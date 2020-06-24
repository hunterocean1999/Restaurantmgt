<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>UPDATE FOOD</title>
</head>

<body>	
	<div class="container">
		<div class="card" style="width: 40rem;">
			<div class="card-body">
				<h2 class="card-title border-bottom border-dark text-center pb-2">Food Information</h2>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-5"> 
							<img src="<c:url value="/uploads/${food.image}" />" height="200" width="200" />
						</div>
						<div class="col-sm-7">
							<div class="row">
								<div class="col"><label>Food Name</label></div>
								<div class="col">${food.foodName}</div>
							</div>
							
							<div class="row">
								<div class="col"><label>Description</label></div>
								<div class="col">${food.description}</div>
							</div>
							
							<div class="row">
								<div class="col"><label>Unit Price</label></div>
								<div class="col"><fmt:formatNumber type="number" maxFractionDigits="0" 
									value="${food.unitPrice}" /> VNĐ</div>
							</div>
							
							<div class="row">
								<div class="col"><label>Food Type</label></div>
								<div class="col">${food.foodType.typeName}</div>
							</div>

							<div class="row">
								<div class="col"><label>Menu Type</label></div>
								<div class="col">${food.menuType.typeName}</div>
							</div>
							
							<div class="form-group alignRight">
								<a href="<c:url value='/shop/buyProduct?id=${food.id}' />" class="btn btn-success">
									Order
								</a> |
								<a href="<c:url value="/"/> ">
									<i class="fas fa-undo-alt btn btn-dark btn-lg"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>

</html>