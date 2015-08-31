package com.xcao.formsDispatcher.taglib

import com.tmobile.xp.foundation.dispatch.DispatchDestination
import com.tmobile.xp.foundation.dispatch.IDispatchService
import com.tmobile.xp.foundation.jump.CustomerIdentifier
import com.xcao.formsDispatcher.foundation.DispatchDestination
import com.xcao.formsDispatcher.foundation.IDispatchService

class WorkflowTagLib {
    static namespace = 'qv'
    //static defaultEncodeAs = 'raw'
    IDispatchService dispatchService

    /**
     *  Generate a link to Start a workflow with a page redirection or modal popup
     *  Sample Usage:
     *      <qv:workflowStart workflow="changeSim" controller="shopping" action="getDeviceDetails" params="[accountNumber:1234567890, subscriberNumber: command.msisdn]">
     *          <span class="btn btn-primary">Change SIM</span>
     *      </qv:workflowStart>
     *
     * @param workflow (required)           workflow to initiate
     * @param controller (required)         url controller
     * @param action (required)             url action
     * @param params (required)             identifies customer, at lease accountNumber is required
     */
    def workflowStart = { attrs, body ->
        def partialTagLib = grailsApplication.mainContext.getBean('com.tmobile.xp.taglib.PartialTagLib')

        DispatchDestination destination = dispatchService.getDestination(attrs.get('workflow') as String)

        Map paramsToPass = attrs.get('params')
        paramsToPass.put('workflow', attrs.get('workflow') as String)

        String target = attrs.get('target')
        if (!target || target == '_top') {
            String url = createLink(controller: 'dispatch', action: 'startWorkflow', params: paramsToPass)  //route through DispatchController for record keeping
            out << """<a href="${url}" class="${attrs.get('class')}" ${destination.isAllowed ? '' : 'disabled'}>"""  << body() << """</a>"""
        } else {
            attrs.put('controller', 'dispatch')
            attrs.put('action', 'startWorkflow')
            out << partialTagLib."$target"(attrs, body)
        }
    }

}
