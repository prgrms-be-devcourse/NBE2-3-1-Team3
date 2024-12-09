<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


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
            border: transparent;
        }

        .summary {
            background-color: #ddd;
            border-top-right-radius: 1rem;
            border-bottom-right-radius: 1rem;
            padding: 4vh;
            color: rgb(65, 65, 65);
        }

        @media (max-width: 767px) {
            .summary {
                border-top-right-radius: unset;
                border-bottom-left-radius: 1rem;
            }
        }

        .row {
            margin: 0;
        }

        .title b {
            font-size: 1.5rem;
        }

        .col-2,
        .col {
            padding: 0 1vh;
        }

        img {
            width: 3.5rem;
        }

        hr {
            margin-top: 1.25rem;
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

        .badge {
            margin-left: 8px; /* 커피 이름과 수량 배지 사이의 간격 */
        }


    </style>

    <title>Coffee Page</title>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
    <h1 class="text-center">Coffee World</h1>
</div>
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul id="coffee-list" class="list-group products">
                <!-- 상품 목록이 동적으로 추가됩니다. -->
            </ul>
            <div id="error-message" class="error-message mt-3"></div>
        </div>
        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>주문 요약</b></h5>
                <div id="cart-summary"></div>
            </div>
            <hr>
            <form>
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email" class="form-control mb-1" id="email">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control mb-1" id="address">
                </div>
                <div class="mb-3">
                    <label for="postcode" class="form-label">우편번호</label>
                    <input type="text" class="form-control" id="postcode">
                </div>
            </form>
            <div class="row pt-2 pb-2 border-top">
                <p class="text-muted small">
                    당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.
                </p>
            </div>
            <div class="row pt-2 pb-2 border-top">
                <h5 class="col">총금액</h5>
                <h5 class="col text-end" id="total-price">0원</h5>
            </div>
            <button class="btn btn-dark col-12" id="checkout-button">결제하기</button>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const cart = []; // 장바구니 데이터를 저장하는 배열
        const coffeeListElement = document.getElementById("coffee-list");
        const cartSummaryElement = document.getElementById("cart-summary");
        const totalPriceElement = document.getElementById("total-price");
        const checkoutButton = document.getElementById("checkout-button");

        // REST API에서 커피 리스트 가져오기
        fetch("/coffees")
            .then(response => {
                if (!response.ok) {
                    throw new Error("서버에서 데이터를 가져오는 데 실패했습니다.");
                }
                return response.json();
            })
            .then(data => {
                renderCoffeeList(data);
            })
            .catch(error => {
                coffeeListElement.innerHTML = "<li class='list-group-item'>상품 데이터를 불러오는 데 실패했습니다.</li>";
            });

        // 상품 목록 렌더링
        function renderCoffeeList(coffees) {
            coffeeListElement.innerHTML = "";
            coffees.forEach(function (coffee) {
                const coffeeItem = document.createElement("li");
                coffeeItem.className = "list-group-item d-flex mt-3";

                const col2 = document.createElement("div");
                col2.className = "col-2";
                const img = document.createElement("img");
                img.className = "img-fluid";
                img.src = "https://i.imgur.com/HKOFQYa.jpeg";
                img.alt = coffee.coffeeName;
                col2.appendChild(img);

                const col = document.createElement("div");
                col.className = "col";
                col.innerHTML =
                    '<div class="row text-muted">커피콩</div>' +
                    '<div class="row">' + coffee.coffeeName + '</div>';

                const priceCol = document.createElement("div");
                priceCol.className = "col text-center price";
                priceCol.textContent = coffee.coffeePrice + "원";

                const actionCol = document.createElement("div");
                actionCol.className = "col text-end action";
                const button = document.createElement("button");
                button.className = "btn btn-small btn-outline-dark";
                button.textContent = "추가";
                button.onclick = function () {
                    addToCart(coffee.coffeeId, coffee.coffeeName, coffee.coffeePrice);
                };
                actionCol.appendChild(button);

                coffeeItem.appendChild(col2);
                coffeeItem.appendChild(col);
                coffeeItem.appendChild(priceCol);
                coffeeItem.appendChild(actionCol);

                coffeeListElement.appendChild(coffeeItem);
            });
        }

        // 장바구니에 상품 추가
        function addToCart(coffeeId, coffeeName, coffeePrice) {
            const existingItem = cart.find(function (item) {
                return item.coffeeId === coffeeId;
            });
            if (existingItem) {
                existingItem.quantity++;
            } else {
                cart.push({ coffeeId: coffeeId, coffeeName: coffeeName, coffeePrice: coffeePrice, quantity: 1 });
            }
            updateCartSummary();
        }

        // 장바구니 요약 업데이트
        function updateCartSummary() {
            cartSummaryElement.innerHTML = ""; // 기존 요약 초기화
            let total = 0;

            cart.forEach(function (item, index) {
                total += item.coffeePrice * item.quantity;

                const cartItem = document.createElement("div");
                cartItem.className = "row mb-2";

                // 상품 이름과 수량 표시
                const itemName = document.createElement("h6");
                itemName.className = "p-0";
                itemName.textContent = item.coffeeName + " ";

                // 수량 표시 배지
                const itemBadge = document.createElement("span");
                itemBadge.className = "badge bg-dark";
                itemBadge.textContent = item.quantity + "개";

                // - 버튼 추가 (배지 스타일 적용)
                const decrementButton = document.createElement("button");
                decrementButton.className = "badge bg-dark"; // 배지 스타일로 설정
                decrementButton.textContent = "-";
                decrementButton.style.border = "none"; // 버튼 기본 테두리 제거
                decrementButton.style.cursor = "pointer"; // 클릭 가능한 포인터 표시
                decrementButton.onclick = function () {
                    decreaseQuantity(index);
                };

                itemName.appendChild(itemBadge);
                itemName.appendChild(decrementButton);
                cartItem.appendChild(itemName);

                cartSummaryElement.appendChild(cartItem);
            });

            totalPriceElement.textContent = total + "원"; // 총금액 업데이트
        }


        function decreaseQuantity(index) {
            const item = cart[index];
            if (item.quantity > 1) {
                item.quantity--; // 수량 감소
            } else {
                cart.splice(index, 1); // 수량이 0개가 되면 장바구니에서 제거
            }
            updateCartSummary(); // 장바구니 요약 업데이트
        }



        // 결제 버튼 클릭 이벤트
        checkoutButton.addEventListener("click", function () {
            const email = document.getElementById("email").value.trim();
            const address = document.getElementById("address").value.trim();
            const postcode = document.getElementById("postcode").value.trim();

            if (!email || !address || !postcode || cart.length === 0) {
                alert("모든 정보를 입력하고 장바구니에 상품을 추가해주세요!");
                return;
            }

            // 1. User 데이터 저장
            const userRequest = { email, address, zipcode: postcode };

            fetch("/users", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userRequest),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("사용자 정보를 저장하는 데 실패했습니다.");
                    }
                })
                .then(() => {
                    // 2. Order 데이터 저장
                    const orderRequest = {
                        email: email,
                        totalPrice: cart.reduce((sum, item) => sum + item.coffeePrice * item.quantity, 0),
                        orderDate: new Date().toISOString(),
                        orderDetails: cart.map((item) => ({
                            coffeeId: item.coffeeId,
                            quantity: item.quantity,
                        })),
                    };

                    return fetch("/order", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(orderRequest),
                    });
                })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("결제 처리에 실패했습니다.");
                    }
                    return response.json();
                })
                .then(() => {
                    alert("결제가 완료되었습니다!");
                    cart.length = 0;
                    updateCartSummary();
                    document.getElementById("email").value = "";
                    document.getElementById("address").value = "";
                    document.getElementById("postcode").value = "";
                })
                .catch((error) => {
                    console.error("결제 오류:", error);
                    alert("결제 중 오류가 발생했습니다. 다시 시도해주세요.");
                });
        });

        /*
        checkoutButton.addEventListener("click", function () {
            const email = document.getElementById("email").value.trim();
            const address = document.getElementById("address").value.trim();
            const postcode = document.getElementById("postcode").value.trim();

            if (!email || !address || !postcode || cart.length === 0) {
                alert("모든 정보를 입력하고 장바구니에 상품을 추가해주세요!");
                return;
            }

            const orderRequest = {
                email: email,
                totalPrice: cart.reduce(function (sum, item) {
                    return sum + item.coffeePrice * item.quantity;
                }, 0),
                orderDate: new Date().toISOString(),
                orderDetails: cart.map(function (item) {
                    return {
                        coffeeId: item.coffeeId,
                        quantity: item.quantity
                    };
                })
            };

            fetch("/order", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(orderRequest)
            })
                .then(function (response) {
                    if (!response.ok) {
                        throw new Error("결제 처리에 실패했습니다.");
                    }
                    return response.json();
                })
                .then(function () {
                    alert("결제가 완료되었습니다!");
                    cart.length = 0;
                    updateCartSummary();
                    document.getElementById("email").value = "";
                    document.getElementById("address").value = "";
                    document.getElementById("postcode").value = "";
                })
                .catch(function (error) {
                    console.error("결제 오류:", error);
                    alert("결제 중 오류가 발생했습니다. 다시 시도해주세요.");
                });
        });
*/


    });




</script>
</body>
</html>
