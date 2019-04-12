package com.example.androiddemo.bean.juheBean

import com.example.androiddemo.base.HISTORYTODAYKEY
import com.example.androiddemo.utils.DataUtils

data class HistoryTodayRequestByEvent(var data : String = DataUtils.acquireDate(), val key : String = HISTORYTODAYKEY)

data class HistoryTodayRequestByID(var e_id : String, val key : String = HISTORYTODAYKEY)

/*-------------------------------------unifyResult------------------------------------  --*/

data class RequestResult<T>(var reason : String, var result : T, var error_code : Int)

/*--------------------------------HistoryBean-----------------------------------*/

data class ResultDate(var day : String, var date : String, var title : String, var e_id : String)

data class HistoryTodayResultByID(var e_id : String, var result : ResultId, val key : String = HISTORYTODAYKEY)

data class ResultId(var content : String, var picNo : String, var picUrl : PicUrlDate, var title : String, var e_id : String)

data class PicUrlDate(var pic_title: Int, var id : Int, var url : String)

/*-------------------------------nbaBean----------------------------------*/
data class NbaResult(var title : String, var statuslist : statuslistBean, var list: List<listBean>, var teammatch : List<teammatchBean>)

data class statuslistBean(var st0: String, var st1: String, var st2: String)

data class listBean(var title: String, var tr : List<DetailedInfo>, var bottomlink : List<linkBean>)

//"time":"04-10 07:00",
//"player1":"黄蜂",
//"player2":"骑士",
//"player1logo":"https:\/\/mat1.gtimg.com\/sports\/nba\/logo\/1602\/30.png",
//"player2logo":"https:\/\/img1.gtimg.com\/sports\/pics\/hv1\/131\/116\/2220\/144385211.png",
//"player1logobig":"https:\/\/mat1.gtimg.com\/sports\/nba\/logo\/1602\/30.png",
//"player2logobig":"https:\/\/img1.gtimg.com\/sports\/pics\/hv1\/131\/116\/2220\/144385211.png",
//"player1url":"",
//"player2url":"",
//"link1url":"",
//"link2url":"",
//"m_link1url":"",
//"link2text":"技术统计",
//"m_link2url":"",
//"status":"0",
//"score":"0:0",
//"link1text":"视频集锦"
data class DetailedInfo(var time: String, var player1 : String, var player2 : String, var player1logo : String
                        , var player2logo : String, var player1logobig : String, var player2logobig : String, var player1url : String
                        , var player2url : String, var link1url : String, var link2url : String, var m_link1url : String, var link2text : String
                        , var m_link2url : String, var status : String, var score : String, var link1text : String)

data class teammatchBean(var name: String, var url : String)

data class linkBean(var text: String, var url : String)