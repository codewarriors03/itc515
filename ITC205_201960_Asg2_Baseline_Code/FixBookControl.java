public class FixBookControl {
	
	private FixBookUI fixBookUi; //variable 'UI' to 'fixBookUi'
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE state; //variable 'StAtE' to 'state'
	
	private library library; //variable 'LIB' to 'library'
	private book curBook; //variable 'Cur_Book' to 'curBook'


	public FixBookControl() {
		this.library = library.getInstance(); //variable 'LIB' to 'library',  method 'INSTANCE()' to 'getInstance()'
		state = CONTROL_STATE.INITIALISED; //variable 'StAtE' to 'state'
	}
	
	
	public void setFixBookUI(FixBookUI fixBookUi) {	//variable 'UI' to 'fixBookUi',  method 'Set_Ui()' to 'setFixBookUI()'
		if (!state.equals(CONTROL_STATE.INITIALISED)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = fixBookUi;	//variable 'UI' to 'fixBookUi'
		fixBookUi.setState(FixBookUI.UI_STATE.READY); //method 'Set_State()' to 'setState()'
		state = CONTROL_STATE.READY; //variable 'StAtE' to 'state'		 
	}


	public void bookScanned(int bookId) { //method 'Book_scanned()' to 'bookScanned()'
		if (!state.equals(CONTROL_STATE.READY)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = library.getBook(bookId); //variable 'Cur_Book' to 'curBook', variable 'LIB' to 'library', method 'Book()' to 'getBook()'
		
		if (curBook == null) { //variable 'Cur_Book' to 'curBook'
			fixBookUi.display("Invalid bookId"); //variable 'UI' to 'fixBookUi'
			return;
		}
		if (!curBook.isDamaged()) { //variable 'Cur_Book' to 'curBook', method 'IS_Damaged()' to 'isDamaged()'
			fixBookUi.display("Book has not been damaged"); //variable 'UI' to 'fixBookUi'
			return;
		}
		fixBookUi.display(curBook.toString()); //variable 'UI' to 'fixBookUi', variable 'Cur_Book' to 'curBook'
		fixBookUi.setState(FixBookUI.UI_STATE.FIXING); //variable 'UI' to 'fixBookUi', method 'Set_State()' to 'setState()'
		state = CONTROL_STATE.FIXING; //variable 'StAtE' to 'state'		
	}


	public void fixBook(boolean isMustFix) { //variable 'MUST_fix' to 'isMustFix', method 'FIX_Book()' to 'fixBook()'
		if (!state.equals(CONTROL_STATE.FIXING)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (isMustFix) { //variable 'MUST_fix' to 'isMustFix'
			library.repairBook(curBook); //variable 'Cur_Book' to 'curBook', variable 'LIB' to 'library', method 'Repair_BOOK()' to 'repairBook()'
		}
		curBook = null; //variable 'Cur_Book' to 'curBook'
		fixBookUi.setState(FixBookUI.UI_STATE.READY); //variable 'UI' to 'fixBookUi', method 'Set_State()' to 'setState()'
		state = CONTROL_STATE.READY; //variable 'StAtE' to 'state'	 	
	}

	
	public void scanningComplete() { //method 'SCannING_COMplete()' to 'scanningComplete()'
		if (!state.equals(CONTROL_STATE.READY)) { //variable 'StAtE' to 'state'
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.setState(FixBookUI.UI_STATE.COMPLETED); //variable 'UI' to 'fixBookUi', method 'Set_State()' to 'setState()'	
	}






}
