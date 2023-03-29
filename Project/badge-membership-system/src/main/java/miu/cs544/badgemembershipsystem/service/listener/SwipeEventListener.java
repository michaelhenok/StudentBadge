package miu.cs544.badgemembershipsystem.service.listener;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.TransactionRequest;
import miu.cs544.badgemembershipsystem.service.TransactionService;
import miu.cs544.badgemembershipsystem.service.events.BadgeSwipeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwipeEventListener {
    private final TransactionService transactionService;

    @EventListener
    public void onEvent(BadgeSwipeEvent event) {
        TransactionRequest transaction = TransactionRequest.builder()
                .locationId(event.getLocationId())
                .planId(event.getPlanId())
                .badgeId(event.getBadgeId())
                .status(event.getStatus())
                .checkerId(event.getCheckerId()).build();

        transactionService.save(transaction);
    }
}