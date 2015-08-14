package com.formPagesDispatchertaglib

import com.formPagesDispatcher.foundation.dispatch.DispatchDestination
import com.formPagesDispatcher.foundation.dispatch.IDispatchService
import com.formPagesDispatcher.foundation.jump.CustomerIdentifier

class WorkflowTagLib {
    static defaultEncodeAs = 'raw'
    static namespace = 'workflow'
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
        CustomerIdentifier customer = new CustomerIdentifier(accountNumber: params.get('accountNumber'))
        String subscriberNumber = attrs.get('subscriberNumber')
        if (subscriberNumber) { customer.subscriberNumber = subscriberNumber }
        DispatchDestination destination = dispatchService.getDestination(attrs.get('workflow') as String)

        Map paramsToPass = attrs.get('params')
        paramsToPass.put('workflow', attrs.get('workflow') as String)

        String display = attrs.get('display')
        if (!display || display != 'popup') {
            String url = createLink(controller: 'dispatch', action: 'startWorkflow', params: paramsToPass)  //route through DispatchController for record keeping
            out << """<a href="${url}" class="${attrs.get('class')}" ${destination.isAllowed ? '' : 'disabled'}>"""  << body() << """</a>"""
        } else {
            //TODO: delegate to Partial . ajaxModal
        }
    }
}
