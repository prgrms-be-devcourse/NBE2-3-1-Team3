<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%

	int userFlag = (Integer)request.getAttribute("userFlag");
	int orderFlag = (Integer)request.getAttribute("orderFlag");
	int deleteFlag = (Integer)request.getAttribute("deleteFlag");

	out.println( "<script type='text/javascript'>" );
	if ( userFlag == 0 && orderFlag == 0 && deleteFlag== 0) {
		out.println( "alert( '주문 성공' );" );
		out.println( "alert( '당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.' );" );
		out.println( "location.href='list.do';" );
	} else if ( orderFlag == 1 ) {
		out.println( "alert( '장바구니에 하나 이상의 상품을 담아주세요.' );" );
		out.println( "location.href='list.do';" );
	} else if ( userFlag == 2 ) {
		out.println("alert( '재주문 감사합니다.' );");
		out.println("location.href='list.do';");
	} else {
		// 비정상
		out.println( "alert( '주문 실패' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>