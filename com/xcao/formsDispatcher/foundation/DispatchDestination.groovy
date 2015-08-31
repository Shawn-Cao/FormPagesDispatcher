package com.xcao.formsDispatcher.foundation

/**
 * Container object to meet Grails need to hold destination as linked list
 * helps render destination as custom tag
 */
class DispatchDestination {
    String controller
    String action
    boolean isAllowed = false

    DispatchDestination(Map<String, String> configedForm) {
        this.controller = configedForm.get('controller')
        this.action = configedForm.get('action')
        if (configedForm.get('allowed') != 'false') { this.isAllowed = true }
    }
}

