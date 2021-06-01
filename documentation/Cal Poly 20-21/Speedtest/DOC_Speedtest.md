# Internet Speedtest

## Overview
The internet speedtest framework uses the JSpeedTest open library from this repository: https://github.com/bertrandmartel/speed-test-lib

This open library allows us to measure upload and download speeds to a number of compatible test servers. 

The bulk of the code occurs in [UpdateService.java](../../../app/femr/business/services/system/UpdatesService.java). 
Two callbacks, one for the upload and one for the download, handle adding the data into the mysql table so that it 
may be displayed on the admin page. 

## Callback example:
```java
// DOWNLOAD SPEED TEST SOCKET
SpeedTestSocket speedTestSocketDownload = new SpeedTestSocket();
speedTestSocketDownload.addSpeedTestListener(new ISpeedTestListener() {
    @Override
    public void onCompletion(SpeedTestReport report) {
        System.out.println("DOWNLOAD [COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
        INetworkStatus download = retrieveNetworkStatuses().getResponseObject().get(1);
        download.setValue((report.getTransferRateBit().
                divide(new BigDecimal(1000000))).setScale(2, RoundingMode.FLOOR).toString());
        networkStatusRepository.update(download);
    }

    @Override
    public void onProgress(float percent, SpeedTestReport report) { }

    @Override
    public void onError(SpeedTestError speedTestError, String errorMessage) {
        response.addError("Network SpeedTest error", speedTestError.toString());
    }
});
speedTestSocketDownload.startFixedDownload("http://ipv4.ikoula.testdebit.info/100M.iso", 5000);
```

Essentially, this creates a new `SpeedTestSocket` from which our test will be contained within. We then add a 
listener, or callback, to the socked to them override some methods in the `ISpeedTestListener` interface. In the 
`onCompletion()` method, we add the download speed to the mysql table and print the rate. The upload test works the 
same way, and simply updates the table when it is complete. 

## TODO
This script is an evolution of another script we had been using all quarter. That script was written in python 
though, and starting it with the right PATH was a pain, so we switched to Java. Unfortunately, this new library 
doesn't have any latency testing built in, and we didn't have time to write our own solution. There is an entry in 
the table already though for latency, so all we need to do is correctly update that value with the accurate latency 
as right now it just fills it with 15 in every case, even when not connected to the internet. 