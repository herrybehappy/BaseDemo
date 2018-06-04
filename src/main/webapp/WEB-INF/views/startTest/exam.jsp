<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include.comm.jsp"%>
<%@ include file="/WEB-INF/include/include.main.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>进入试卷</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="countdown">
					考试时间： <span id="clock"></span>
				</div>

				<div class="row clearfix">
					<div class="col-md-12 column">
						<form role="form">
							<p>第一题：XXXXXXXXXXXX</p>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="A" checked> A
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios2" value="B"> B
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="C" checked> C
								</label>
							</div>
								<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="D" checked> D
								</label>
							</div>
						</form>
					</div>
					<div class="row clearfix">
						<div class="col-md-6 column">
							<button class="btn btn-default" type="button">上一题</button>
							<button class="btn btn-default" type="button">下一题</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		$('#clock').countdown('2020/10/10 12:34:56').on('update.countdown',
				function(event) {
					var format = '%H:%M:%S';
					$(this).html(event.strftime(format));
				}).on(
				'finish.countdown',
				function(event) {
					$(this).html('This offer has expired!').parent().addClass(
							'disabled');

				});
	</script>
</body>
</html>