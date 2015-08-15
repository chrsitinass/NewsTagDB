import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Operation {
    private Mysql News;
    private Mysql NewsTag;
    private Statement statm;
    private Statement statm_tag;
    private ArrayList news_doc_id;
    private ArrayList news_source_id;
    private ArrayList update_time;

    public Operation(String ip, String dbUser, String dbPassword)
        throws SQLException {
        this.News = new Mysql("newsProject", ip, dbUser, dbPassword);
        this.NewsTag = new Mysql("NewsTag", ip, dbUser, dbPassword);
        this.statm = News.conn.createStatement();
        this.statm_tag = NewsTag.conn.createStatement();
        this.news_doc_id = new ArrayList<Integer> ();
        this.news_source_id = new ArrayList<String> ();
        this.update_time = new ArrayList<String> ();
    }

    public void getCorrectNewsId()
        throws SQLException {
        String sql = "SELECT docId, sourceId, source FROM NewsHash WHERE isDupNews = 0 OR isDupNews = 1;";
        ResultSet result_set = statm_tag.executeQuery(sql);
        while (result_set.next()) {
            String source = result_set.getString(3);
            if (source.equals("xhs.trs")) continue;
            news_doc_id.add(result_set.getInt(1));
            news_source_id.add(result_set.getString(2));
        }
    }

    public void getUpdateTime()
        throws SQLException {
        for (int ind = 0; ind < news_doc_id.size(); ind += 1) {
            Integer doc_id = (Integer) news_doc_id.get(ind);
            String source_id = (String) news_source_id.get(ind);
            String source = source_id.split("_")[0];
            String id = source_id.split("_")[1];
            while (true) {
                String trans_source_id = source + "\\_" + id;
                String sql = "SELECT pubtime FROM news WHERE outer_id = '" + trans_source_id + "';";
                ResultSet rs = statm.executeQuery(sql);
                if (rs.next()) {
                    String upd_sql = "UPDATE NewsList SET pubtime = '" + rs.getString(1) + "' WHERE docId = "
                            + doc_id.toString();
                    System.out.println(upd_sql);
                    statm_tag.executeUpdate(upd_sql);
                    break;
                } else {
                    id = "0" + id;
                }
                if (id.length() > 20) break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Operation op = new Operation("172.31.19.9", "root", "");
        op.getCorrectNewsId();
        op.getUpdateTime();
    }
}
