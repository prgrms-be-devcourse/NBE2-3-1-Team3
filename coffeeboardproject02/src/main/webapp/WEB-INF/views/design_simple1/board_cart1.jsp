<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%

	int userFlag = (Integer)request.getAttribute("userFlag");
	int orderFlag = (Integer)request.getAttribute("orderFlag");

	out.println( "<script type='text/javascript'>" );
	if ( userFlag == 0 && orderFlag == 0) {
		// 정상
		out.println( "alert( '주문 성공' );" );
		out.println( "alert( '당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.' );" );
		out.println( "location.href='list.do';" );
	} else {
		// 비정상
		out.println( "alert( '주문 실패' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>