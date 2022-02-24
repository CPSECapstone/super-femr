package femr.business.helpers;

import java.io.IOException;

public class RollbackHelpers {
  public static void backUpSQL() {
    try {
      String[] cmd = new String[]{"/bin/sh", "/app/util/backup/backup.sh"};
      Process pr = Runtime.getRuntime().exec(cmd);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
