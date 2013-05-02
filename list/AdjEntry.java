package list;

public class AdjEntry{
	public ListNode partner;
	public ListNode start;
	public ListNode end;
	
	public AdjEntry(ListNode p, ListNode s, ListNode e){
		partner = p;
		start = s;
		end = e;
	}
}
