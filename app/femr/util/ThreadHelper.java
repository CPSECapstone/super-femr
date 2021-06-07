package femr.util;

import femr.business.services.core.IUpdatesService;
import femr.data.models.core.INetworkStatus;
import java.util.List;
import java.util.Map;

import femr.common.dtos.ServiceResponse;
import play.Logger;

public class ThreadHelper implements Runnable {

    private final IUpdatesService internetStatusService;

    public ThreadHelper(IUpdatesService internetStatusService) {
        this.internetStatusService = internetStatusService;
    }

    @Override
    public void run() {
        ServiceResponse<List<? extends INetworkStatus>>
            updateResponse = internetStatusService.updateNetworkStatuses();

        try {
            // Wait for the internet script to execute
            Thread.sleep(10000);
            if (updateResponse.hasErrors()) {
                Logger.error("Issues reported from the internet test script: ");
                for (Map.Entry<String, String> entry : updateResponse.getErrors().entrySet()) {
                    Logger.error(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (InterruptedException e) {
            Logger.error("Thread Helper Interrupted: ", e);
        }
    }
}
