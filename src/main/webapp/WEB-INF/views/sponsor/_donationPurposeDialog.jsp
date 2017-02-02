<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
    div.modal-dialog { width: 700px; }
    div#scroll { height: 600px; width: 100%; overflow-y: scroll; }
    #scroll tbody tr:hover { background-color: #ffe; cursor: pointer; }
</style>

<div id="donationPurposeDialog" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>기부목적 검색</h3>
      </div>
      <div class="modal-body">
        <div id="scroll">
          <table class="table table-bordered" style="width: 100%;">
            <thead>
              <tr>
                <th>기관</th>
                <th>기관종류</th>
                <th>기부목적</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="donationPurpose" items="${ donationPurposes }">
              <tr data-id="${ donationPurpose.id }">
                <td>${ donationPurpose.corporateName }</td>
                <td>${ donationPurpose.organizationName }</td>
                <td>${ donationPurpose.name }</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script>
    $(document).ready(function() {
        $("#donationPurposeDialog #scroll tr").click(function() {
            var tr = $(this);
            var donationPurposeId = tr.attr("data-id");
            var corporateName = tr.find("td:nth-child(1)").text();
            var organizationName = tr.find("td:nth-child(2)").text();
            var donationPurposeName = tr.find("td:nth-child(3)").text();
            $("span#donationPurposeName").text(corporateName + ' / ' + organizationName + ' / ' + donationPurposeName);
            $("input[name*=donationPurposeName]").val(corporateName + ' / ' + organizationName + ' / ' + donationPurposeName);
            $("input[name*=donationPurposeId]").val(donationPurposeId);
            $("#donationPurposeDialog").modal('toggle');
        })
    });
</script>
