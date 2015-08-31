package com.xcao.formsDispatcher.foundation

/**
 * A Workflow resembles series of forms (which will make changes on backend customer state)
 * should contain currentIndex to present p
 * can be predefined or dynamically assembled
 *
 */
class Workflow {
    public static final String SEPARATOR = '=>'

    private int currentIndex = 0
    private LinkedList<DispatchDestination> forms = new LinkedList<DispatchDestination>()

    Workflow(List<Map<String, String>> config) {
        config.each{ Map<String, String> eachForm ->
            forms.add(new DispatchDestination(eachForm))
        }
    }

    DispatchDestination getCurrent() { forms.get(currentIndex) }
    DispatchDestination Continue() {
        if (currentIndex > 0) return forms.get(++currentIndex)
        return null
    }
    DispatchDestination Back() {
        if (currentIndex < forms.size()) return forms.get(--currentIndex)
        return null
    }

    LinkedList<DispatchDestination> getForms() { forms } //TODO: package this class later
}
