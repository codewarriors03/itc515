public class FixBookControl {
	
	private FixBookUI fixBookUi; //variable 'UI' to 'fixBookUi'
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE state; //variable 'StAtE' to 'state'
	
	private library library; //variable 'LIB' to 'library'
	private book curBook; //variable 'Cur_Book' to 'curBook'


	public FixBookControl() {
		this.library = library.INSTANCE(); //variable 'LIB' to 'library'
		state = CONTROL_STATE.INITIALISED; //variable 'StAtE' to 'state'
	}
	
	
	public void Set_Ui(FixBookUI fixBookUi) {	//variable 'UI' to 'fixBookUi'
		if (!state.equals(CONTROL_STATE.INITIALISED)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = fixBookUi;	//variable 'UI' to 'fixBookUi'
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY; //variable 'StAtE' to 'state'		 
	}


	public void Book_scanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = library.Book(bookId); //variable 'Cur_Book' to 'curBook', variable 'LIB' to 'library'
		
		if (curBook == null) { //variable 'Cur_Book' to 'curBook'
			fixBookUi.display("Invalid bookId"); //variable 'UI' to 'fixBookUi'
			return;
		}
		if (!curBook.IS_Damaged()) { //variable 'Cur_Book' to 'curBook'
			fixBookUi.display("Book has not been damaged"); //variable 'UI' to 'fixBookUi'
			return;
		}
		fixBookUi.display(curBook.toString()); //variable 'UI' to 'fixBookUi', variable 'Cur_Book' to 'curBook'
		fixBookUi.Set_State(FixBookUI.UI_STATE.FIXING); //variable 'UI' to 'fixBookUi'
		state = CONTROL_STATE.FIXING; //variable 'StAtE' to 'state'		
	}


	public void FIX_Book(boolean isMustFix) { //variable 'MUST_fix' to 'isMustFix'
		if (!state.equals(CONTROL_STATE.FIXING)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (isMustFix) { //variable 'MUST_fix' to 'isMustFix'
			library.Repair_BOOK(curBook); //variable 'Cur_Book' to 'curBook', variable 'LIB' to 'library'
		}
		curBook = null; //variable 'Cur_Book' to 'curBook'
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY); //variable 'UI' to 'fixBookUi'
		state = CONTROL_STATE.READY; //variable 'StAtE' to 'state'	 	
	}

	
	public void SCannING_COMplete() {
		if (!state.equals(CONTROL_STATE.READY)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.Set_State(FixBookUI.UI_STATE.COMPLETED); //variable 'UI' to 'fixBookUi'		
	}






}
