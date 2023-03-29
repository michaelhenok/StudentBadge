package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.BadgeRequest;
import miu.cs544.badgemembershipsystem.dto.request.BadgeSwipeRequest;
import miu.cs544.badgemembershipsystem.dto.response.BadgeResponse;

import java.util.List;

public interface BadgeService {
    List<BadgeResponse> findAll();

    BadgeResponse findById(long id);

    List<BadgeResponse> findBadgesByMemberID(long memberId);

    void save(BadgeRequest badgeRequest);

    void swipe(long id, BadgeSwipeRequest badgeSwipeRequest);

    void update(long id, BadgeRequest badgeRequest);

    void delete(long id);


}
