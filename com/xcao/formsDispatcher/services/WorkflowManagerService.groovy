package com.xcao.formsDispatcher.services

import com.xcao.formsDispatcher.foundation.Workflow

/**
 * Config management service to talk with config/DB to add, change, and remove workflows
 * Shawn - this is just initial thought
 */
class WorkflowManagerService {
    def grailsApplication
    private Map<String, Map<String, String>> getForms() { return grailsApplication.config.forms as Map<String, Map<String, String>> }

    Workflow createWorkflow(String workflowName, boolean saveWorkflow=false) {
        List<String> formsSequence = assembleFormsSequence()  //TODO: add parameter and finish algorithm

        if (saveWorkflow) {
            Map<String, String> workflows = grailsApplication.config.workflows
            workflows.put(workflowName, formsSequence.join(Workflow.SEPARATOR))
        }

        List<Map<String, String>> requiredForms = new ArrayList<HashMap<String, String>>()
        formsSequence.each {
            requiredForms.add(getForms().get(it))
        }

        return new Workflow(requiredForms)
    }

    /**
     * from a goal, get required forms. arrange them into a list and return
     * //TODO: implement this
     * @return
     */
    private List<String> assembleFormsSequence() {
        List<String> formsSequence = new ArrayList<String>()
        return formsSequence
    }

    private Map
}

