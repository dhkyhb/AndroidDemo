package com.example.androiddemo.services

import com.example.androiddemo.bean.AgentTraBeanD.AgentTraBean
import com.example.androiddemo.bean.AgentTraBeanD.AgentTraReq
import com.example.androiddemo.common.AGENT_TRANSACTION
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MerchantServices {

    @POST(AGENT_TRANSACTION)
    fun acquireAgentTransaction(@Body agentTraReq: AgentTraReq): Observable<AgentTraBean>

}