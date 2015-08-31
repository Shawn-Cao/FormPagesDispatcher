package com.xcao.formsDispatcher.foundation

/**
 * Center piece of UI dynamic dispatching framework
 * generate attributes in the format of String to support related UI components
 */
interface IDispatchService {
    DispatchDestination getDestination(String workflowName)
    DispatchDestination getDestination(Workflow workflow)
}