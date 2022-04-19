<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href='${cPath }/resources/js/fullcalendar-5.11.0/lib/main.min.css' rel='stylesheet' />
 <script src='${cPath }/resources/js/fullcalendar-5.11.0/lib/main.min.js'></script>
 
 <div id='calendar'></div>
 
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog  modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">직원 상세 조회 중 일부만!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<table class="table table-bordered" id="empTable">
      		<tr>
      		<th>직원번호</th>
      		<td id="td_employeeId"></td>
      		</tr>
      		<tr>
      		<th>직원명</th>
      		<td id="td_empName"></td>
      		</tr>
      		<tr>
      		<th>고용일</th>
      		<td id="td_hireDateStr"></td>
      		</tr>
      		<tr>
      		<th>급여</th>
      		<td id="td_salary"></td>
      		</tr>
      	</table>
      </div>
    </div>
  </div>
</div>
 
 <script>
  let empModal = $("#exampleModal").on("hidden_bs_modal", function(){
	  $(this).find("td").html("");
  });
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth'
      , locale : 'KR'
      , events : '${cPath}/example/fullCalendar.json'
      , initialDate : '2021-01-01'
      , eventClick: function(info) {
    		let event = info.event;
    		let employee = event.extendedProps["originData"];
    		for(let propName in employee){
    			empModal.find("#td_"+propName).html(employee[propName]);
    		}
    		empModal.modal('show');
      }
    });
    calendar.render();
  });

</script>