package pers.mtx.mt;

import java.io.IOException;
import java.sql.SQLException;

public interface Crud {
    public String make(String uri,byte[] content) throws IOException, SQLException;
}
