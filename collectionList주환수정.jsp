<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/includeHeader.jsp"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/include/includeTitle.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/themes/smoothness/jquery-ui.css" />" />
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.tablesorter.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/jquery-ui-1.11.4/jquery-ui.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.ui.datepicker.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/string.js"/>"></script>

<script type="text/javascript">
/*
function fncInsert() {
	var frm = document.frmCollectionList;

	isOpen = window.open('', 'popFrm', 'toolbar=no,location=no,status=yes,menubar=no,scrollbars=no,resizable=no,width=600,height=520, top=30,left=100');

	frm.elements["keyParam"].value = "";
	frm.target = "popFrm";
	frm.action = "<c:url value="/program/programGroupForm"/>";
	frm.submit();
}
*/
$(document).ready(function(){
	var keyword = '${paramMap.keyWord}';
	console.log("keyword=> " + keyword);
	if(keyword!=''){
		$('#keyWord').focus();
	}
})
	
	// 10개씩 보기 , 25개씩 보기 , 50개씩 보기 ( 한페이지당 보여줄 데이터의 갯수를 불러오는 함수입니다. )
function fncPageSize(pageSize) {
	var frm	= document.frmKofaCollectionList;
	frm.elements["keyParam"].value = "kofaCollectionList";
	frm.elements["page"].value = "1";
	frm.target="_self";
	frm.action = "<c:url value="/kofaCollection/kofaCollectionList"/>";
	frm.submit();
}
		
		
// 다음 페이지를 보여줍니다 ( pageNation Function() )
function fncGoPage(nPage){
	var frm	= document.frmKofaCollectionList;
	frm.elements["keyParam"].value = "kofaCollectionList";
	frm.elements["page"].value	= nPage;
	frm.target = "_self";
	frm.action = "<c:url value="/kofaCollection/kofaCollectionList"/>";
	//"<c:url value="/kofaCollection/kofaCollectionList"/>"
	frm.submit();
}
// 클릭한 해당게시물의 상세조회 함수 입니다. ( view Function() )
function fncDetail(id)
{

	var frm	= document.frmKofaCollectionList;
	frm.elements["keyParam"].value = "kofaCollectionListInfoView";
	frm.elements["collectionId"].value = id;
	frm.target = "_self";
	frm.action = "<c:url value="/kofaCollection/kofaCollectionListInfoView"/>";
	frm.submit();
}
// 게시물을 등록하는 버튼을 눌렀을때 실행되는 이벤트 함수입니다. ( insert Function() )
function fncInsert() {
	var frm = document.frmKofaCollectionList;
	frm.elements["keyParam"].value = "KofaCollectionListInsert";
	frm.target = "_self";
	frm.action = "<c:url value="/kofaCollection/KofaCollectionListForm"/>";
	frm.submit();
}

// 게시물 검색기능 
function fncSearch() {
	var frm = document.frmKofaCollectionList;
	
	 var keyWord = frm.elements["keyWord"].value;
	 var kofaUseYn = frm.elements["kofaUseYn"].value;
	 var KofaGroupTitle = frm.elements["KofaGroupTitle"].value;

	var keyField = frm.elements["keyField"].value;
	frm.elements["keyParam"].value = "kofaCollectionListSearch";
	frm.elements["page"].value = "1";
	frm.target = "_self";
	frm.action = "<c:url value="/kofaCollection/kofaCollectionList"/>";
	frm.submit();
}
// 초기 리스트 화면으로 검색 결과를 초기화시킵니다.
	function fncSearchFirst(){
		
		var frm	= document.frmKofaCollectionList;
		//frm.elements["exhibitionType"].value = "";
		frm.elements["keyParam"].value = "kofaCollectionList";
		frm.elements["KofaGroupTitle"].value="";
		frm.elements["kofaUseYn"].value="";
		frm.elements["keyField"].value = "";
		frm.elements["keyWord"].value = "";
		frm.elements["sortField"].value = "";
		frm.elements["sortWord"].value = "DESC";
		frm.elements["pageSize"].value = "10";
		frm.elements["page"].value = "1";
		frm.target = "_self";
		frm.action = "<c:url value="/kofaCollection/kofaCollectionList"/>";
		frm.submit();
	}
</script>
</head>

<body>

<c:set var="listSize" value="${fn:length(listResult.messages) }"/>
	<c:choose>
		<c:when test="${not empty paramMap.page }"><c:set var="page" value="${paramMap.page }"/></c:when>
		<c:otherwise><c:set var="page" value="1"/></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty paramMap.sortField }">
			<c:set var="sortField" value="${paramMap.sortField }"/>
			<c:set var="sortWord" value="${paramMap.sortWord }"/>
		</c:when>
		<c:otherwise>
			<c:set var="sortField" value="C_COLLECTION_ID"/>
			<c:set var="sortWord" value="DESC"/>
		</c:otherwise>
	</c:choose>

	<form method="post" id="frmKofaCollectionList" name="frmKofaCollectionList">
		<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
		<input type="hidden" name="keyParam" />
		<input type="hidden" name="collectionId" />
		<input type="hidden" name="page" value="${page }" />
		<input type="hidden" name="sortField" value="${sortField }" />
		<input type="hidden" name="sortWord" value="${sortWord }" />
		<div>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<col>
							<col width="350">
							<tr height="30">
								<td class="redfont_bold">컬렉션 리스트</td>
								<td align="right"></td>
							</tr>
							<tr height="2" bgcolor="#b50000">
								<td colspan="2"></td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="5" cellspacing="0"
							style="margin-top: 10px;">
							<col>
							<col width="160">
							<tr height="30">
								<td>
									<table width="100%" border="0" cellpadding="5" cellspacing="1"
										class="tablesorter">
										<col width="120">

										<tr>
											<td>컬렉션 그룹</td>
											<td><select name="KofaGroupTitle" id="KofaGroupTitle">
										
											<option value="">전체</option>
											<!--  select 사용자가 선택한값이 select box 에서 유지되도록!  -->
								<c:forEach var="listResult" items="${collectionGroup}" varStatus="status">
									<option value="<c:out value="${listResult.cCollectionKofaGroupTitle }" />" 
										<c:if test="${KofaGroupTitle == listResult.cCollectionKofaGroupTitle }">selected="selected"</c:if>>
										<c:out value="${listResult.cCollectionKofaGroupTitle }" />
									</option>
								</c:forEach>
														
											</select></td>

												
										</tr>
										<tr>
											<td>사용유무</td>
											<td><select name="kofaUseYn" id="kofaUseYn">
													<option value="">전체</option>
													
													<c:forEach var="useYnFlag" items="${useYnFlag}" varStatus="status">
									<option value="<c:out value="${useYnFlag.useYn }" />" 
										<c:if test="${kofaUseYn == useYnFlag.useYn }">selected="selected"</c:if>>
										<c:out value="${useYnFlag.useYn }" />
									</option>
								</c:forEach>
													
													
											</select></td>
										</tr>
										<tr>
											<td>컬렉션</td>
											<td>
										
									<input type="hidden" name="keyField" value="kofaCollectionListTitle">
									<input type="text" id="keyWord" name="keyWord" value="${paramMap.keyWord}" onkeypress="if(event.keyCode==13){fncSearch();return false;}">
									<span class="btn_type6_1" onclick="fncSearch();"><a href="#none">검색</a></span>
									<span class="btn_type7_1" onclick="fncSearchFirst();"><a href="#none">초기화</a></span>		
												</td>
										</tr>
									</table>
								</td>
								<td style="text-align:right;vertical-align:top;padding-top:15px;">
							<span class="btn_type2_1" onclick="fncInsert();"><a href="#none">등록</a></span>
							
							<select name="pageSize" onchange="fncPageSize(this.options[this.options.selectedIndex].value)">
							<option value="10" <c:if test="${ paramMap.pageSize eq '10' }">selected</c:if>>10</option>
							<option value="25" <c:if test="${ paramMap.pageSize eq '25' }">selected</c:if>>25</option>
							<option value="50" <c:if test="${ paramMap.pageSize eq '50' }">selected</c:if>>50</option>
							</select>
						</td>
							</tr>
						</table>
						<table border="0" cellpadding="5" cellspacing="1"
							style="table-layout: fixed; width: 100%;" class="tablesorter">
							<col width="40">
							<col width="100">
							<col width="90">
							<col width="80">
							<col width="80">
							<col width="80">
							<thead>
								<tr height="30">
									
									<th style="text-align: left;" class="">번호</th>
									<th style="text-align: left;" class="${sortMap.sort_0 }"
										onclick="">컬렉션 그룹</th>
									<th style="text-align: left;" class="${sortMap.sort_1 }"
										onclick="">컬렉션</th>
										<!-- 
									<th style="text-align: left;" class="${sortMap.sort_2 }"
										onclick="">등록자료건수</th>
										 -->
									<th style="text-align: left;" class="${sortMap.sort_3 }"
										onclick="">수정일</th>
									<th style="text-align: left;" class="${sortMap.sort_5 }"
										onclick="">사용유무</th>
									<th style="text-align: left;" class="${sortMap.sort_4 }"
										onclick="">수정</th>
									
								</tr>
							</thead>
							<tbody>
						<c:forEach var="listResult" items="${listResult.messages}" varStatus="status">
							<c:choose>
								<c:when test="${status.count%2 == 0 }"><c:set var="trClass" value="odd" /></c:when>
								<c:otherwise><c:set var="trClass" value="" /></c:otherwise>
							</c:choose>
								<tr class="${trClass }" align="center">
								
									<td>${listResult.rowNo } </td>
									<td>${listResult.cCollectionKofaGroupTitle }</td>
									<td>${listResult.cCollectionTitle }</td>
									<td>${listResult.cUpdateDateTime }</td>
								    <td>${listResult.useYn }</td>
								   <td><span class="btn_type8_1" onclick="fncDetail('${listResult.cCollectionId }')"><a href="#none">수정</a></span></td>
								</tr>
								</c:forEach>
							<c:if test="${listSize<1 }">
							<tr bgcolor="#FFFFFF">
								<td colspan="10" height="289" style="text-align:center;vertical-align:middle;">
									등록된 데이터가 없습니다.
								</td>
							</tr>
							</c:if>
							
							</tbody>
						</table>
						<table width="100%" border="0" cellpadding="5" cellspacing="0">
							<tr height="30">
								<td align="center">${pageNavigation }</td>
							</tr>
							<tr height="40">
								<td align="left">
						<img src="<c:url value="/static/images/icon/0.gif"/>" style="background-color:#666666;width:2px;height:2px;margin:0 6px 2px 0" />
						<span id="tot_div">
						조회결과 : 총 <b><fmt:formatNumber value="${listResult.totalCount }" type="number" /></b> 건
						</span>
						</td>
							</tr>
						</table> <img src="<c:url value="/static/images/icon/0.gif"/>" height="50" /><br />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
