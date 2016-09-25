<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body>
	<h3>기부목적별 납입현황</h3>
	<form method="post">
		<div class="row">
			<div class="col-lg-9">
				<label> 기간(시작-끝) </label> <input type="date" name="startDate">
				~ <input type="date" name="endDate">
			</div>
			<div class="col-lg-3">
				<div id="column-right">
					<button type="submit" class="btn btn-reversed">검색</button>
				</div>

			</div>
		</div>
		<h4>기부목적별 납입보고서</h4>
		<span>납입기간:</span>${startDate}~${endDate}

		<table>
			<thead>
				<th>총 후원인수</th>
				<th>총 납입수</th>
				<th>총 납입액</th>
			</thead>
			<tbody>
				<tr>
					<td>${totalSponsor}</td>
					<td>${totalDonationPurpose}</td>
					<td>${totalSum}</td>
				</tr>
			</tbody>
		</table>


		<table>
			<thead>
				<th>기부목적</th>
				<th>기부후원인수</th>
				<th>납입수</th>
				<th>금액</th>
				<th>비율</th>
			</thead>

			<c:forEach var="payment" items="${ list }">
				<tbody>
					<tr>
						<td>${payment.donationPurpose}</td>
						<td>${payment.count1}
						<td>${payment.count2}
						<td>${payment.sum}
						<td>${payment.percent}%</td>
					</tr>
			</c:forEach>
			<tr>
				<td>소계</td>
				<td>${totalSponsor}</td>
				<td>${totalDonationPurpose}</td>
				<td>${totalSum}</td>
				<td>${totalPercent}%</td>
			</tr>
			</tbody>
		</table>

	</form>
</body>