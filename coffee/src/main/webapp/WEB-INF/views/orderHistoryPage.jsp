<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const searchButton = document.getElementById("search-button");
            const emailInput = document.getElementById("email-input");
            const tableBody = document.getElementById("order-table-body");

            searchButton.addEventListener("click", function () {
                const email = emailInput.value.trim();
                if (!email) {
                    alert("Please enter an email address.");
                    return;
                }

                // Fetch orders by email
                fetch("/orders/mail?email=" + encodeURIComponent(email))
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Failed to fetch orders for email: " + email);
                        }
                        return response.json();
                    })
                    .then(data => {
                        renderOrders(data);
                    })
                    .catch(error => {
                        console.error("Error fetching orders:", error);
                        alert("Error fetching orders. Check the console for more details.");
                    });
            });

            function renderOrders(orders) {
                tableBody.innerHTML = ""; // Clear previous data
                if (orders.length === 0) {
                    const row = document.createElement("tr");
                    const cell = document.createElement("td");
                    cell.colSpan = 4;
                    cell.textContent = "No orders found.";
                    cell.classList.add("text-center", "text-muted");
                    row.appendChild(cell);
                    tableBody.appendChild(row);
                    return;
                }

                orders.forEach(order => {
                    const row = document.createElement("tr");

                    const orderIdCell = document.createElement("td");
                    orderIdCell.textContent = order.orderId;
                    row.appendChild(orderIdCell);

                    const emailCell = document.createElement("td");
                    emailCell.textContent = order.email;
                    row.appendChild(emailCell);

                    const totalPriceCell = document.createElement("td");
                    totalPriceCell.textContent = order.totalPrice + "원";
                    row.appendChild(totalPriceCell);

                    const orderStateCell = document.createElement("td");
                    orderStateCell.textContent = calculateOrderState(order.orderDate);
                    row.appendChild(orderStateCell);

                    tableBody.appendChild(row);
                });
            }

            function calculateOrderState(orderDate) {
                const now = new Date();
                const orderDateTime = new Date(orderDate);
                const todayTwoPM = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 14, 0, 0);
                const yesterdayTwoPM = new Date(todayTwoPM.getTime() - 24 * 60 * 60 * 1000);

                if (orderDateTime < yesterdayTwoPM) {
                    return "배송완료";
                } else if (orderDateTime < todayTwoPM) {
                    return "배송예정";
                } else {
                    return "결제완료";
                }
            }
        });
    </script>
    <style>
        body {
            background: #f8f9fa;
        }

        .container {
            margin-top: 50px;
        }

        h1 {
            margin-bottom: 30px;
        }

        table {
            background: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Order History</h1>
    <div class="mb-4">
        <label for="email-input" class="form-label">Email을 입력하세요</label>
        <div class="input-group">
            <input type="email" id="email-input" class="form-control" placeholder="Enter email">
            <button id="search-button" class="btn btn-dark">Search</button>
        </div>
    </div>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>주문ID</th>
            <th>Email</th>
            <th>총 금액</th>
            <th>배송 상태</th>
        </tr>
        </thead>
        <tbody id="order-table-body">
        <!-- Orders will be rendered here -->
        </tbody>
    </table>
</div>
</body>
</html>
