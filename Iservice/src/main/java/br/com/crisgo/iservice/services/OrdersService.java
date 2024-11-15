package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestOrdersDTO;
import br.com.crisgo.iservice.DTO.response.ResponseOrdersDTO;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.*;
import br.com.crisgo.iservice.repositorys.OrdersRepository;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public OrdersService(OrdersRepository ordersRepository, UserRepository userRepository, ProductRepository productRepository, SellerRepository sellerRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public ResponseOrdersDTO createOrder(Long userId, Long productId, Long sellerId, RequestOrdersDTO requestOrdersDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setProduct(product);
        orders.setSeller(seller);
        orders.setStatus(requestOrdersDTO.getStatus());
        orders.setTotalPrice(requestOrdersDTO.getTotalPrice());

        Orders savedOrder = ordersRepository.save(orders);
        return mapToResponseOrdersDTO(savedOrder);
    }

    public List<ResponseOrdersDTO> findByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        List<Orders> orders = ordersRepository.findByUser(user);

        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma compra feita por esse usuario foi encontrada");
        }

        return orders.stream()
                .map(this::mapToResponseOrdersDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!ordersRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID " + id + " não encontrado");
        }
        ordersRepository.deleteById(id);
    }

    @Transactional
    public ResponseOrdersDTO updateOrder(Long id, RequestOrdersDTO requestOrdersDTO) {
        Orders existingOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra de ID " + id + " não encontrado"));

        existingOrder.setStatus(requestOrdersDTO.getStatus());
        existingOrder.setTotalPrice(requestOrdersDTO.getTotalPrice());

        Orders updatedOrder = ordersRepository.save(existingOrder);
        return mapToResponseOrdersDTO(updatedOrder);
    }

    private ResponseOrdersDTO mapToResponseOrdersDTO(Orders orders) {
        ResponseOrdersDTO response = new ResponseOrdersDTO();
        response.setId(orders.getOrderId());
        response.setStatus(orders.getStatus());
        response.setTotalPrice(orders.getTotalPrice());
        return response;
    }
}
