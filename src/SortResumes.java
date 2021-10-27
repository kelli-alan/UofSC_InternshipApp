import java.util.ArrayList;

/*
 *
 * @authors Evan Grunewald
 */
public class SortResumes {


  /**
   * Returns a given resume list in reverse order
   * 
   * @param resumes List of resumes to be sorted
   */
    public static ArrayList<Resume> sortByTimeRecieved(ArrayList<Resume> resumes) {
        int n = resumes.size();
        ArrayList<Resume> ret = new ArrayList<Resume>();
        int j = n;
        for (int i = 0; i < n; i++) {
            ret.set(j - 1, resumes.get(i));
            j = j - 1;
        }

        return ret;
    }
}

