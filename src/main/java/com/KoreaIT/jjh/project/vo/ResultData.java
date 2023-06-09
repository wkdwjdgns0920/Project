package com.KoreaIT.jjh.project.vo;

import java.util.Map;

import com.KoreaIT.jjh.project.util.Ut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultData<DT> {
	
	private String resultCode;
	private String msg;
	private DT data1;
	private String data1Name;
	private Map<String, Object> body;

	public ResultData(String resultCode, String msg, Object... args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.body = Ut.mapOf(args);
	}
	
	
	//	ResultData
	public static <DT> ResultData<DT> from (String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}
	
	//	ResultData 오버라이딩
	public static <DT> ResultData<DT> from (String resultCode, String msg,String data1Name ,DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		
		rd.resultCode = resultCode;	//	성공,실패에 대한 결과값
		rd.msg = msg;	//	성공,실패에 대한 메시지
		rd.data1 = data1;	//	결과에 대한 데이터
		rd.data1Name = data1Name;	//	결과에 대한 데이터 이름
		
		return rd;
	}
	
	//	"S-"로 시작시에 성공
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	//	Success값이 실패일 경우 true
	public boolean isFail() {
		return isSuccess() == false;
	}
	
	//	조회수에 대한 값을 보여주기 위해 만듬
	public static <DT> ResultData<DT> newData(ResultData rd, String data1Name, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), data1Name, newData);
	}
}
