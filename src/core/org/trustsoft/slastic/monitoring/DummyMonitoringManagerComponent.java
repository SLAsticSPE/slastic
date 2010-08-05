package org.trustsoft.slastic.monitoring;

/**
 * A monitoring manager that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyMonitoringManagerComponent extends AbstractMonitoringManagerComponent {

    public boolean execute() {
        return true;
    }

    public void terminate(boolean error) {
        // do nothing
    }

}
