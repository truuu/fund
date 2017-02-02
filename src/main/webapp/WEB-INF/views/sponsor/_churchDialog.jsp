<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
    div.modal-dialog { width: 400px; }
    div#scroll { height: 500px; width: 100%; overflow-y: scroll; }
    #scroll tbody tr:hover { background-color: #ffe; cursor: pointer; }
</style>

<div id="churchDialog" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>교회 검색</h3>
      </div>
      <div class="modal-body">
        <div id="scroll">
          <table class="table table-bordered" style="width: 100%;">
            <tbody>
              <c:forEach var="church" items="${ churchList }">
                <tr>
                    <td>${ church.id }</td>
                    <td>${ church.codeName }</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button onclick="cancelChurch()" class="btn btn-gray" data-dismiss="modal">소속교회 없음</button>
        <button class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script>
    $(document).ready(function() {
        $("#churchDialog #scroll tr").click(function() {
            var tr = $(this);
            var churchId = tr.find("td:nth-child(1)").text();
            console.log(churchId);
            var churchName = tr.find("td:nth-child(2)").text();
            $("span#churchName").text(churchName);
            $("input[name*=churchName]").val(churchName);
            $("input[name*=churchId]").val(churchId);
            $("#churchDialog").modal('toggle');
        })
    });
    function cancelChurch() {
        $("span#churchName").text('');
        $("input[name=churchId]").val(0);
    }
</script>
