<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        h1 {
            margin: 20px 0;
            text-align: center;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
        }

        table {
            width: 100%;
            margin-bottom: 20px;
        }

        table th, table td {
            text-align: center;
        }
    </style>
    <title>관리자 배송 조회</title>
</head>
<body>
<h1>관리자 배송 조회</h1>
<div class="container">
    <h3>배송 완료</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Email</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <tbody id="completed-orders-body">
        <!-- 배송 완료 데이터 -->
        </tbody>
    </table>

    <h3>배송 예정</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Email</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <tbody id="scheduled-orders-body">
        <!-- 배송 예정 데이터 -->
        </tbody>
    </table>

    <h3>결제 완료</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Email</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <tbody id="payment-completed-orders-body">
        <!-- 결제 완료 데이터 -->
        </tbody>
    </table>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const completedTable = document.getElementById("completed-orders-body");
        const scheduledTable = document.getElementById("scheduled-orders-body");
        const paymentCompletedTable = document.getElementById("payment-completed-orders-body");

        // Fetch and render each table
        fetchOrders("/orders/completed", completedTable);
        fetchOrders("/orders/scheduled", scheduledTable);
        fetchOrders("/orders/payment-completed", paymentCompletedTable);

        function fetchOrders(url, tableBody) {
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Failed to fetch data from ${url}`);
                    }
                    return response.json();
                })
                .then(data => renderTable(data, tableBody))
                .catch(error => console.error("Error fetching orders:", error));
        }

        function renderTable(data, tableBody) {
            tableBody.innerHTML = ""; // Clear previous content

            if (data.length === 0) {
                const emptyRow = document.createElement("tr");
                const emptyCell = document.createElement("td");
                emptyCell.colSpan = 3;
                emptyCell.textContent = "데이터가 없습니다.";
                emptyRow.appendChild(emptyCell);
                tableBody.appendChild(emptyRow);
                return;
            }

            data.forEach(order => {
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

                tableBody.appendChild(row);
            });
        }
    });
</script>
</body>
</html>
