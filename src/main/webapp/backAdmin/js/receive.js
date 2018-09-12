/**
 *
 * @param type
 * @param code
 */
function changeArriveType(type){
    if(type == 1){
        $("#arriveFlightNoLabel").text("航班号");
        $("#arriveAirportLabel").text("到达机场");
        $("#arriveAirport").html('<option value="1">哈尔滨太平国际机场</option>');
    }else{
        $("#arriveFlightNoLabel").text("出发车次");
        $("#arriveAirportLabel").text("到达车站");
        var str= '<option value="2" >哈尔滨站</option>'
            +'<option value="3" >哈尔滨西站</option>'
            +'<option value="4" >哈尔滨东站</option>'
            +'<option value="5" >哈尔滨北站</option>';
        $("#arriveAirport").html(str);
    }

    $("#arriveFlightNo").val("");
    $("#arriveTime").val("");
    $("#arriveAirport").get(0).selectedIndex=0;
}

/**
 *
 * @param type
 * @param code
 */
function changeReturnType(type){
    if(type == 1){
        $("#returnFlightNoLabel").text("航班号");
        $("#returnAirportLabel").text("回程起飞机场");
        $("#returnAirport").html('<option value="1">哈尔滨太平国际机场</option>');
    }else{
        $("#returnFlightNoLabel").text("回程车次");
        $("#returnAirportLabel").text("回程车站");
        var str= '<option value="2" >哈尔滨站</option>'
            +'<option value="3" >哈尔滨西站</option>'
            +'<option value="4" >哈尔滨东站</option>'
            +'<option value="5" >哈尔滨北站</option>';
        $("#returnAirport").html(str);
    }
    $("#returnFlightNo").val("");
    $("#returnTime").val("");
    $("#returnAirport").get(0).selectedIndex=0;
}

function hideSendElements(){
    //隐藏
    $("#arriveTypeLabel").hide();
    $("#arriveTypeForm").hide();
    $("#arriveFlightNoLabel").hide();
    $("#arriveFlightNoForm").hide();
    $("#arriveTimeLabel").hide();
    $("#arriveTimeForm").hide();
    $("#arriveAirportLabel").hide();
    $("#arriveAirportForm").hide();
    $("#returnTypeLabel").hide();
    $("#returnTypeForm").hide();
    $("#returnFlightNoLabel").hide();
    $("#returnFlightNoForm").hide();
    $("#returnTimeLabel").hide();
    $("#returnTimeForm").hide();
    $("#returnAirportLabel").hide();
    $("#returnAirportForm").hide();
    //清空数据
    $("#arriveType1").attr("checked","checked");
    $("#arriveType2").removeAttr("checked");
    $("#arriveFlightNo").val("");
    $("#arriveTime").val("");
    $("#arriveAirport").get(0).selectedIndex=0;
    $("#returnType1").attr("checked","checked");
    $("#returnType2").removeAttr("checked");
    $("#returnFlightNo").val("");
    $("#returnTime").val("");
    $("#returnAirport").get(0).selectedIndex=0;
}

function showSendElements(){
    $("#arriveTypeLabel").show();
    $("#arriveTypeForm").show();
    $("#arriveFlightNoLabel").show();
    $("#arriveFlightNoForm").show();
    $("#arriveTimeLabel").show();
    $("#arriveTimeForm").show();
    $("#arriveAirportLabel").show();
    $("#arriveAirportForm").show();
    $("#returnTypeLabel").show();
    $("#returnTypeForm").show();
    $("#returnFlightNoLabel").show();
    $("#returnFlightNoForm").show();
    $("#returnTimeLabel").show();
    $("#returnTimeForm").show();
    $("#returnAirportLabel").show();
    $("#returnAirportForm").show();
}