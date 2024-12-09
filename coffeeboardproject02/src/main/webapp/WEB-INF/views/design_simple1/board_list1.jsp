<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.coffeeboardproject.dto.Coffee" %>
<%@ page import="com.example.coffeeboardproject.dto.Cart" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ArrayList<Coffee> coffeeLists = (ArrayList<Coffee>) request.getAttribute("coffeeLists");
	System.out.println("ArrayList<Coffee>" + coffeeLists);

	int coffee_id = 0;
	String coffee_name = null;
	int coffee_price = 0;

	StringBuilder sbHtml = new StringBuilder();
    for (Coffee coffee : coffeeLists) {

        coffee_id = coffee.getCoffee_id();
        coffee_name = coffee.getCoffee_name();
        coffee_price = coffee.getCoffee_price();

        sbHtml.append("<li class='list-group-item d-flex mt-2'>");
        sbHtml.append("<div class='col-2'><img class='img-fluid' src='https://i.imgur.com/HKOFQYa.jpeg' alt=''></div>");
        sbHtml.append("<div class='col'>");
        sbHtml.append("<div class='row text-muted'>커피콩</div>");
        sbHtml.append("<div class='row'>" + coffee_name + "</div>");
        sbHtml.append("</div>");
        sbHtml.append("<div class='col text-center price'>" + coffee_price + "</div>");
        sbHtml.append("<div class='col text-end action'><a class='btn btn-small btn-outline-dark' href='./list.do?coffee_id=" + coffee_id + "' name='" + coffee_id + "'>추가</a></div>");
        sbHtml.append("</li>");
	}

	// 추가 클릭 전까진 null 출력
	System.out.println("coffee_id1: " + request.getParameter("coffee_id"));
	//=======================================================================================================================

	StringBuilder sbHTML = new StringBuilder();
	int totalPrice = 0;
	if (request.getParameter("coffee_id") != null) {
		System.out.println("coffee_id2: " + request.getParameter("coffee_id"));
		int result = (Integer) request.getAttribute("cartFlag");
		List<Cart> cartSelectAll = (List<Cart>) request.getAttribute("cartLists");
		if ( result == 1 ) {

			if (cartSelectAll != null && !cartSelectAll.isEmpty()) {
				for ( Cart cart : cartSelectAll) {
					for (Coffee coffee : cart.getCoffee()) {
						String coffeeName = coffee.getCoffee_name();

						int quantity = cart.getQuantity();

						sbHTML.append("<div class='row' id='cartContainer'>");
						sbHTML.append("<h6 class='p-0' >" + coffeeName + " <span class='badge bg-dark'>" + quantity + "개</span ></h6>");
						sbHTML.append("</div >");

						int coffeePrice = coffee.getCoffee_price();  // 커피의 가격

						// 커피 가격과 수량을 기반으로 가격 계산 예시
						totalPrice += quantity * coffeePrice;
					}
				}
			} else {
				System.out.println("Coffee 리스트가 비어 있습니다.");
			}

		}
	}



%>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<style>
		body {
			background: #ddd;
		}

		.card {
			margin: auto;
			max-width: 950px;
			width: 90%;
			box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			border-radius: 1rem;
			border: transparent
		}

		.summary {
			background-color: #ddd;
			border-top-right-radius: 1rem;
			border-bottom-right-radius: 1rem;
			padding: 4vh;
			color: rgb(65, 65, 65)
		}

		@media (max-width: 767px) {
			.summary {
				border-top-right-radius: unset;
				border-bottom-left-radius: 1rem
			}
		}

		.row {
			margin: 0
		}

		.title b {
			font-size: 1.5rem
		}

		.col-2,
		.col {
			padding: 0 1vh
		}

		img {
			width: 3.5rem
		}

		hr {
			margin-top: 1.25rem
		}
		.products {
			width: 100%;
		}
		.products .price, .products .action {
			line-height: 38px;
		}
		.products .action {
			line-height: 38px;
		}

	</style>
	<title>Hello, world!</title>
	<!--
	<script type="text/javascript">
		document.querySelectorAll('addToCartBtn').forEach(button => {
			button.onclick = function() {
				const coffee_id = this.getAttribute('data-coffee-id');  // data-coffee-id 값을 가져옵니다.

				// Ajax 요청을 통해 서버에 추가 요청
				fetch('/list.do', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					// coffee_id를 JSON 형식으로 전달
					body: JSON.stringify({ coffee_id: coffee_id })
				})
				.then(response => response.json())
				.then(data => {
					if (data.success) {
					// 서버에서 받은 coffee 데이터로 HTML을 동적으로 생성하여 추가
						const coffeeName = data.coffeeName;
						const quantity = data.quantity;
						const coffeePrice = data.coffeePrice;

						const cartContainer = document.getElementById('cartContainer');
						const coffeeHTML = `
							<div class='row'>
								<h6 class='p-0'>${coffeeName} <span class='badge bg-dark'>${quantity}개</span></h6>
							</div>
						`;
						cartContainer.innerHTML += coffeeHTML;  // 이전 내용 뒤에 새로운 항목을 추가
					} else {
						alert('추가 실패');
					}
				})
				.catch(error => console.error('Error:', error));
			};
		});
	</script>
	-->

	<script type="text/javascript">
		window.onload = function() {
			document.getElementById( 'pbtn' ).onclick = function() {
				//alert( '버튼 클릭' );
				if ( document.pfrm.email.value == '' ) {
					alert( '이메일을 입력하셔야 합니다.' );
					return false;
				}

				if ( document.pfrm.address.value == '' ) {
					alert( '주소를 입력하셔야 합니다.' );
					return false;
				}

				if ( document.pfrm.postcode.value == '' ) {
					alert( '우편번호를 입력하셔야 합니다.' );
					return false;
				}

				document.pfrm.submit();
			};
		};
	</script>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
	<h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card">
	<div class="row">
		<div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
			<h5 class="flex-grow-0"><b>상품 목록</b></h5>
			<ul class="list-group products">
				<%=sbHtml.toString()%>
			</ul>
		</div>
		<div class="col-md-4 summary p-4">
			<div>
				<h5 class="m-0 p-0"><b>Summary</b></h5>
			</div>
			<hr>
			<!--
			<div class="row">
				<h6 class="p-0">Columbia Nariñó <span class="badge bg-dark text-">2개</span></h6>
			</div>
			<div class="row">
				<h6 class="p-0">Brazil Serra Do Caparaó <span class="badge bg-dark">2개</span></h6>
			</div>
			<div class="row">
				<h6 class="p-0">Columbia Quindio (White Wine Extended Fermentation) <span class="badge bg-dark">2개</span></h6>
			</div>
			<div class="row">
				<h6 class="p-0">Ethiopia Sidamo <span class="badge bg-dark">2개</span></h6>
			</div>
			-->
			<%=sbHTML.toString() %>
			<!--
			<script type="text/javascript">
				var sbHTML = "<%=sbHTML.toString() %>"; // sbHTML 값을 JavaScript 변수로 전달

				if (!sbHTML || sbHTML.trim() === "") {
					alert("내용이 없습니다.");
				}
			</script>
			-->

			<form action="/manager.do" name="pfrm">
				<div class="mb-3">
					<label for="email" class="form-label">이메일</label>
					<!--폼의 **id**는 HTML 내에서 요소의 스타일링이나 JavaScript에서의 DOM 접근에 사용-->
					<!--name이 반드시 필요-->
					<input type="email" class="form-control mb-1" id="email" name="email">
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">주소</label>
					<input type="text" class="form-control mb-1" id="address" name="address">
				</div>
				<div class="mb-3">
					<label for="postcode" class="form-label">우편번호</label>
					<input type="text" class="form-control" id="postcode" name="postcode">
				</div>
				<div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>

				<div class="row pt-2 pb-2 border-top">
					<h5 class="col">총금액</h5>
					<h5 class="col text-end"><%=totalPrice%></h5>
					<input type="hidden" name="totalPrice" value="<%=totalPrice%>">
				</div>
				<button class="btn btn-dark col-12" id="pbtn">결제하기</button>
			</form>

		</div>
	</div>
</div>
</body>
</html>