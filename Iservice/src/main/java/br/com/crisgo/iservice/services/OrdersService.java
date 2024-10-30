package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.*;
import br.com.crisgo.iservice.repositorys.OrdersRepository;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Orders createOrder(Long userId, Long productId, Long sellerId, Orders orders) {
        // Finding all the entity to link to Orders entity
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        // Linking all the entities
        orders.setUser(user);
        orders.setProduct(product);
        orders.setSeller(seller);

        return ordersRepository.save(orders);
    }

    public List<Orders> findByUser(Long userId) {
        // Finding the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        // F\inding and setting orders linked to the user
        List<Orders> orders = ordersRepository.findByUser(user);
        // Checking if order exists
        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma compra feita por esse usuario foi encontrada");
        }
        return orders;
    }
    @Transactional
    public void deleteById(Long id) {
        if (!ordersRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID " + id + " não encontrado");
        }
        productRepository.deleteById(id);
    }


    @Transactional
    public Orders updateOrder(Long id, Orders orderDetails ) {
        // Find existing order
        Orders existingOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra de ID " + id + " não encontrado"));

        // Update fields
        existingOrder.setStatus(orderDetails.getStatus());

        // Save the updated order
        return ordersRepository.save(existingOrder);
    }
}
