// General Description
// Package: Checkers
// Purpose: Represents a single piece in the checkers game, holding information about the piece's color ('w' for white, 'b' for black) and its position on the board.
// Methods:
// Constructors to set the piece's color.
// getColour() and setPosition(Cell p) for accessing and updating the piece's color and position.
// getPosition() for retrieving the current position.
// getAvailableMoves(Cell[][] board) (not implemented) intended to calculate possible moves.
// capture() and promote() methods (not fully implemented) for handling captures and promotions of pieces.
// draw(App app) for drawing the piece on the board using Processing methods.

package Checkers;

import java.util.HashSet;

public class CheckersPiece {

	// The color of the checkers piece ('w' for white, 'b' for black)
	private char colour;

	// The current position of the piece on the board
	private Cell position;

    // check if there is a jump
    private boolean isJump = false;

    // isKing - tells whether king or not
    private boolean isKing = false;

    // captured
    private boolean isCaptured = false;

	// Constructor: Initializes a new piece with a given color
	public CheckersPiece(char c) {
		this.colour = c;
	}

	// Returns the color of the piece
	public char getColour() {
		return this.colour;
	}

	// Sets the position of the piece to a given cell
	public void setPosition(Cell p) {
		this.position = p;
	}

	// Returns the current position of the piece
	public Cell getPosition() {
		return this.position;
	}

    public void removePosition() {
        this.position = null;
    }

    public boolean getIsJump() {
        return this.isJump;
    }

    public boolean getIsKing() {
        return this.isKing;
    }

    public boolean getIsCaptured() {
        return this.isCaptured;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }

    public void setIsCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public void setIsJump(boolean isJump) {
        this.isJump = isJump;
    }

    
    //copy piece
    public CheckersPiece copyPiece() {
        CheckersPiece copied = new CheckersPiece(this.getColour());
        copied.setPosition(this.getPosition());
        copied.setIsJump(this.getIsJump());
        copied.setIsKing(this.getIsKing());
        copied.setIsCaptured(this.getIsCaptured());
        return copied;
    }


	
	public HashSet<Cell> getAvailableMoves(Cell[][] board) {
        HashSet<Cell> available = new HashSet<Cell>();
		//TODO: Get available moves for this piece depending on the board layout, and whether this piece is a king or not
		//How to record if the move is a capture or not? Maybe make a new class 'Move' that stores this information, along with the captured piece?
		int xValue = this.position.getX();
        int yValue = this.position.getY();

        // white piece
        if (this.getColour() == 'w') {
            // check left piece
            if (xValue-1>=0 && yValue+1<8) {
                if (board[yValue+1][xValue-1].getPiece()==null) {
                    available.add(board[yValue+1][xValue-1]);

                //check jump
                } else {
                    if (xValue-2>=0 && yValue+2<8) {
                        if (board[yValue+2][xValue-2].getPiece()==null) {
                            this.isJump = true;
                            available.add(board[yValue+2][xValue-2]);
                        }
                    }
                }
            }

            // check right piece
            if (xValue+1<8 && yValue+1<8) {
                if (board[yValue+1][xValue+1].getPiece()==null) {
                    available.add(board[yValue+1][xValue+1]);

                // check jump  
                } else {
                    if (xValue+2<8 && yValue+2<8) {
                        if (board[yValue+2][xValue+2].getPiece()==null) {
                            this.isJump = true;
                            available.add(board[yValue+2][xValue+2]);
                        }
                    }
                }
            }

            // for kings - check backwards
            if (this.isKing == true) {
                // check left
                if (xValue-1>=0 && yValue-1>=0) {
                    if (board[yValue-1][xValue-1].getPiece()==null) {
                        available.add(board[yValue-1][xValue-1]);
                    
                    // check jump
                    } else {
                        if (xValue-2>=0 && yValue-2>=0) {
                            if (board[yValue-2][xValue-2].getPiece()==null) {
                                this.isJump = true;
                                available.add(board[yValue-2][xValue-2]);
                            }
                        }
                    }
                }

                // check right
                if (xValue+1<8 && yValue-1>=0) {
                    if (board[yValue-1][xValue+1].getPiece()==null) {
                        available.add(board[yValue-1][xValue+1]);

                    // check jump
                    } else {
                        if (xValue+2<8 && yValue-2>=0) {
                            if (board[yValue-2][xValue+2].getPiece()==null) {
                                this.isJump = true;
                                available.add(board[yValue-2][xValue+2]);
                            }
                        }
                    }
                }

            }

        }


        // black piece
        if (this.getColour() == 'b') {
            // check left piece
            if (xValue-1>=0 && yValue-1>=0) {
                if (board[yValue-1][xValue-1].getPiece()==null) {
                    available.add(board[yValue-1][xValue-1]);

                //check jump
                } else {
                    if (xValue-2>=0 && yValue-2>=0) {
                        if (board[yValue-2][xValue-2].getPiece()==null) {
                            this.isJump = true;
                            available.add(board[yValue-2][xValue-2]);
                        }
                    }
                }
            }

            // check right piece
            if (xValue+1<8 && yValue-1>=0) {
                if (board[yValue-1][xValue+1].getPiece()==null) {
                    available.add(board[yValue-1][xValue+1]);

                // check jump
                } else {
                    if (xValue+2<8 && yValue-2>=0) {
                        if (board[yValue-2][xValue+2].getPiece()==null) {
                            this.isJump = true;
                            available.add(board[yValue-2][xValue+2]);
                        }
                    }
                }
            }

            
            if (this.isKing == true) {
                // check left
                if (xValue-1>=0 && yValue+1<8) {
                    if (board[yValue+1][xValue-1].getPiece()==null) {
                        available.add(board[yValue+1][xValue-1]);
                    
                    // check jump
                    } else {
                        if (xValue-2>=0 && yValue+2<8) {
                            if (board[yValue+2][xValue-2].getPiece()==null) {
                                this.isJump = true;
                                available.add(board[yValue+2][xValue-2]);
                            }
                        }
                    }
                }

                // check right
                if (xValue+1<8 && yValue+1<8) {
                    if (board[yValue+1][xValue+1].getPiece()==null) {
                        available.add(board[yValue+1][xValue+1]);

                    // check jump
                    } else {
                        if (xValue+2<8 && yValue+2<8) {
                            if (board[yValue+2][xValue+2].getPiece()==null) {
                                this.isJump = true;
                                available.add(board[yValue+2][xValue+2]);
                            }
                        }
                    }
                }    
            }

        }


        return available;
	}

    
    public HashSet<Cell> copyAvailableMoves(Cell[][] board) {
        HashSet<Cell> availableCopy = new HashSet<Cell>();
        
        for (Cell i : this.getAvailableMoves(board)) {
            availableCopy.add(i);
        }

        return availableCopy;
    }
	
	public boolean capture() {
		//capture this piece
        this.isCaptured = true;
        this.position.removePiece();
        return this.isCaptured;
	}
	
	public boolean promote() {
		//promote this piece
        if (this.colour == 'w' && this.position.getY() == 7) {
            this.isKing = true;
        } else if (this.colour == 'b' && this.position.getY() == 0) {
            this.isKing = true;
        }

        return this.isKing;
	}
	
	// Draws the piece on the board using the Processing library.
    // This method takes an instance of the App class, which extends PApplet from Processing, to access drawing methods.
	public void draw(App app)
	{
		// Set the stroke weight for the outline of the piece
		app.strokeWeight(5.0f);

		if (this.colour == 'w') {
			// White piece
			app.fill(255); // white fill
			app.stroke(0); // black stroke
		} else if (this.colour == 'b') {
		    // Black piece
			app.fill(0); // black fill
			app.stroke(255);// white stroke
		}

		// Draw the piece as an ellipse (circle) at the piece's position, adjusting the coordinates based on the cell size
		// The method elipse takes 4 parameters
		// Syntax:  ellipse(a, b, c, d)	
		// Parameters
		// a	(float)	x-coordinate of the ellipse
		// b	(float)	y-coordinate of the ellipse
		// c	(float)	width of the ellipse by default
		// d	(float)	height of the ellipse by default
        app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.8f, App.CELLSIZE*0.8f);
		
        if (this.isKing == true && this.colour == 'w') {
            app.fill(255);
            app.stroke(0);
            app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.4f, App.CELLSIZE*0.4f);
        } else if (this.isKing == true && this.colour == 'b') {
            app.fill(0);
            app.stroke(255);
            app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, position.getY()*App.CELLSIZE + App.CELLSIZE/2, App.CELLSIZE*0.4f, App.CELLSIZE*0.4f);
        }
        
        // Disable the stroke for subsequent drawings
		app.noStroke();
	}
}