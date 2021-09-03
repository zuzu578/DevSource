<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/includeHeader.jsp"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/include/includeTitle.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/themes/smoothness/jquery-ui.css" />" />
<style type="text/css">
li {
	list-style: none;
}

#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#sortable li {
	margin: 0 3px 3px 3px;
	padding: 5px;
	padding-left: 10px;
	background: #fff;
}
</style>
<script type="text/javascript"
	src="<c:url value="/static/js/jquery-ui-1.11.4/jquery-ui.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.tablesorter.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/string.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
	
	var isOpen;

	<c:if test="${not empty msgResult }" >
		<c:if test="${not empty paramMap.keyParam && (paramMap.keyParam eq 'programInfoUpdate') }" >
			isOpen = window.open('', 'popFrm', 'toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=600,height=520, top=30,left=100');
			isOpen.close();
		</c:if>
	
	alert("${msgResult}");
	</c:if>

	$(document).ready(function(){
		
		$("#movieList").tablesorter({
			headers: {
				7: {sorter: false}
			}
		});
	});

	function fncList(){
		var frm = document.frmProgramManage;
		frm.elements["keyParam"].value = "programInfoList";
		frm.target = "_self";
		frm.action = "<c:url value="/program/programInfoList"/>";
		frm.submit();
	}

	function fncDownload(keyFile) {
		var frm = document.frmProgramManage;
		if(keyFile!=""){
			frm.elements["keyFile"].value = keyFile;
			frm.target = "_self";
			frm.action = "<c:url value="/common/dfload" />";
			frm.submit();
		}
	}

	function fncInsert() {
		var frm = document.frmProgramManage;

		frm.elements["keyParam"].value = "programInfoInsert";
		frm.target = "_self";
		frm.action = "<c:url value="/program/programInfoForm"/>";
		frm.submit();
	}

	function fncUpdate() {
		var frm = document.frmProgramManage;

		frm.elements["keyParam"].value = "programInfoUpdate";
		frm.target = "_self";
		frm.action = "<c:url value="/program/programInfoForm"/>";
		frm.submit();
	}
	
	
	function fncFileDelete(id) {
		if(confirm("삭제하시겠습니까?")) { 
			var frm = document.frmProgramManage;
			frm.elements["keyParam"].value = "attachFileDelete";
			frm.elements["fileId"].value = id;
			frm.target = "_self";
			frm.action = "<c:url value="/common/attachFileManage"/>";
			frm.submit();
		}
	}
	function fncDelete() {
		var frm = document.frmProgramManage;
		if(confirm("삭제하시겠습니까?")) { 
			frm.elements["keyParam"].value = "programInfoDelete";
			frm.target = "_self";
			frm.action = "<c:url value="/program/promgramInfoManage"/>";
			frm.submit();
		}
	}
	function fncDetail(id)
	{
		var frm	= document.frmProgramMovieManage;
		frm.elements["keyParam"].value = "programMovieInfoView";
		frm.elements["movieId"].value = id;
		frm.target = "_self";
		frm.action = "<c:url value="/program/programMovieInfoView"/>";
		frm.submit();
	}

</script>
</head>

<body>

	<div>
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<col>
						<col width="350">
						<tr height="30">
							<td class="redfont_bold">컬렉션 리스트 수정</td>
							<td align="right">${navigation }</td>
						</tr>
						<tr height="2" bgcolor="#b50000">
							<td colspan="2"></td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td><span class="btn_type1_1" onclick="fncList();"><a
									href="#none">목록</a></span> <span class="btn_type2_1"
								onclick="fncInsert();"><a href="#none">등록</a></span> <span
								class="btn_type3_1" onclick="fncUpdate();"><a
									href="#none">수정</a></span> <span class="btn_type4_1"
								onclick="fncDelete();"><a href="#none">삭제</a></span></td>
						</tr>
					</table>

					<table style="width: 100%;" border="0" cellspacing="1"
						cellpadding="1" class="tablesorter">
						<col width="100">
						<col width="280">
						<col width="100">
						<col>
						<tr>
							<th bgcolor="#F0F0F6">컬렉션 그룹</th>
							<td colspan="3">${collectionListVo.cCollectionKofaGroupTitle}</td>
						</tr>
						<tr>
							<th bgcolor="#F0F0F6">컬렉션명</th>
							<td colspan="3">${collectionListVo.cCollectionTitle}</td>
						</tr>


						<tr>
							<th bgcolor="#F0F0F6">대표이미지</th>
							<td colspan="3">${collectionListVo.cCollectionKofaGroupImg}
							</td>

						</tr>
						<tr>
							<th bgcolor="#F0F0F6">등록번호</th>
							<td>${collectionListVo.cCollectionId}</td>
							<th bgcolor="#F0F0F6">사용유무</th>
							<td>${collectionListVo.useYn}</td>
						</tr>
					</table>

					<table style="width: 100%;" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><img
								src="<c:url value="/static/images/icon/n_setup.gif"/>"
								name="Image11" border="0" /> <B>컬렉션 설명</B></td>

						</tr>
					</table>
					<table style="width: 100%;" border="0" cellspacing="1"
						cellpadding="1" class="tablesorter">
						<tr valign="top">
							<td><textarea style="border: 0; width: 100%; resize: none">${collectionListVo.cCollectionInfo }</textarea>
							</td>
						</tr>
					</table>
					<table style="width: 100%;" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><img
								src="<c:url value="/static/images/icon/n_setup.gif"/>"
								name="Image11" border="0" /> <B>이용조건</B></td>
						</tr>
					</table>
					<table style="width: 100%;" border="0" cellspacing="1"
						cellpadding="1" class="tablesorter">
						<tr valign="top">
							<!-- 
							<td><c:out
									value="${fn:replace(fn:replace(programDescriptionEn.programDescription, crlf, '<br/>'), lf, '<br/>') }"
									escapeXml="false" /></td> -->
							<td><textarea style="border: 0; width: 100%; resize: none">${collectionListVo.cCollectionUseCondition }</textarea>
							</td>

						</tr>
					</table>
					<table style="width: 100%;" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><img
								src="<c:url value="/static/images/icon/n_setup.gif"/>"
								name="Image11" border="0" /> <B>컬렉션 해제</B></td>
						</tr>
					</table>
					<table style="width: 100%;" border="0" cellspacing="1"
						cellpadding="1" class="tablesorter">
						<tr valign="top">
							<td><textarea id="collExplanation" name="collExplanation"
									style="background: #FFF4E1; width: 100%; height: 200px;">${collectionListVo.cCollectionDesc }</textarea>
								<span class="my_error_display"></span></td>
						</tr>
					</table>


					<table style="width: 100%;" border="0" cellspacing="1"
						cellpadding="1" class="tablesorter">

						<tr>
							<th bgcolor="#F0F0F6">해제원고</th>
							<td colspan="3">--></td>
						</tr>
						<tr>
							<th bgcolor="#F0F0F6">연구보고서</th>
							<td colspan="3"></td>
						</tr>
					</table>


					<table width="100%" border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td><span class="btn_type1_1" onclick="fncList();"><a
									href="#none">목록</a></span> <span class="btn_type2_1"
								onclick="fncInsert();"><a href="#none">등록</a></span> <span
								class="btn_type3_1" onclick="fncUpdate();"><a
									href="#none">수정</a></span> <span class="btn_type4_1"
								onclick="fncDelete();"><a href="#none">삭제</a></span></td>
						</tr>
					</table>

				</td>
			</tr>
		</table>
	</div>
	<form method="post" id="frmProgramManage" name="frmProgramManage">

	</form>

	<form method="post" id="frmProgramMovieManage"
		name="frmProgramMovieManage">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="hidden" name="keyParam"
			value="" /> <input type="hidden" name="movieId" value="" /> <input
			type="hidden" name="leftMenuCss" value="left_menu_hanfont_bold" />
	</form>

	<script type="text/javascript">
    $(function() {
        $("#sortable").sortable({
        });
        $("#sortable").disableSelection();
    });

	
</script>
	<%@ include file="/WEB-INF/jsp/include/includeFooter.jsp"%>

</body>
</html>
<script>
window.onload = function() {
	CKEDITOR.replace('collExplanation', {
		width : '100%',
		height : '200',
		autoGrow_minHeight : '200',
		resize_enabled : false,
		customConfig : '/static/ckeditor/config_custom.js'
	});
};
</script>