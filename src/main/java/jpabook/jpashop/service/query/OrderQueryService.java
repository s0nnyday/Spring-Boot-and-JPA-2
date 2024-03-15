package jpabook.jpashop.service.query;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class OrderQueryService {

    /**
     * OSIV 끄면 영속성 컨텍스트 필요한 코드 트랜잭션 안에서 처리. 컨트롤러에서는 호출만
     *
     public List<OrderDto> orderV3() {
     List<Order> orders = orderRepository.findAllwithItem();

     List<OrderApiController.OrderDto> result = orders.stream()
     .map(o -> new OrderApiController.OrderDto(o))
     .collect(Collectors.toList());

     return result;
     }
     */


    //영속성 컨텍스트 사용할 코드 가져오기. 컨트롤러에는 호출만

}
