package com.xcao.formsDispatcher.services

import com.xcao.formsDispatcher.foundation.DispatchDestination
import com.xcao.formsDispatcher.foundation.IDispatchService
import com.xcao.formsDispatcher.foundation.Workflow
import com.xcao.formsDispatcher.foundation.WorkflowException

/**
 * dispatch service desi
 */
class DispatchService implements IDispatchService {
    def grailsApplication
    WorkflowManagerService workflowManagerService
    private Map<String, String> getWorkflows() { return grailsApplication.config.workflows as Map<String, String> }
    private Map<String, Map<String, String>> getForms() { return grailsApplication.config.forms as Map<String, Map<String, String>> }

    private Workflow getWorkflow(String workflowName) {
        if (workflows.containsKey(workflowName)) {  //predefined
            List<Map<String, String>> configedWorkflow = new ArrayList<HashMap<String, String>>()

            List<String> workflowSequence = workflows.get(workflowName).split(Workflow.SEPARATOR)
            workflowSequence.each {
                configedWorkflow.push(forms.get(it))
            }
            if (configedWorkflow.size() != 0) {
                return new Workflow(configedWorkflow)
            } else {
                throw new WorkflowException('Workflow Configuration Error: Could not map existing forms into workflow')
            }
        } else {    //assemble new
            return workflowManagerService.createWorkflow(workflowName, true)
        }
    }

    @Override
    DispatchDestination getDestination(String workflowName) {
        return getWorkflow(workflowName)?.getCurrent()
    }

    @Override
    DispatchDestination getDestination(Workflow workflow) {
        return workflow?.getCurrent()
    }

    private List<String> getIdentifiers(String workflow) {  //WorkflowTagLib could use this to automatically extract identifiers from params
        List<String> identifiers = getWorkflow(workflow).get('identifiers')?.toString()?.split(',')

        return identifiers
    }
}