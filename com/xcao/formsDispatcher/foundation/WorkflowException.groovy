package com.xcao.formsDispatcher.foundation

/**
 * Exception related with Workflow creating, parsing and configuration
 */
class WorkflowException extends RuntimeException {
    WorkflowException() {
    }

    WorkflowException(String var1) {
        super(var1)
    }

    WorkflowException(String var1, Throwable var2) {
        super(var1, var2)
    }

    WorkflowException(Throwable var1) {
        super(var1)
    }
}

