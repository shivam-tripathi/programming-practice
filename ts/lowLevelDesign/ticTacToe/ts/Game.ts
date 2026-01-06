import readline from 'readline';

class Reader {
  rl: readline.Interface = readline.createInterface({ input: process.stdin, output: process.stdout })

  async read(msg: string): Promise<string> {
    return new Promise(resolve => {
      this.rl.question(`${msg}\n`, resolve);
    })
  }

  close() {
    this.rl.close();
  }
}

type STATE = 'X' | 'O' | '_';
const states: STATE[] = ['X', 'O'];

const reader = new Reader();

class Player {
  private moves: number[][]  = [];
	constructor(public readonly name: string, public readonly sign: STATE) {}
  async makeMove() {
    try {
      const positions = await reader.read(`${this.name}, make your move! Input x and y (zero indexed)`);
      const [x, y] = positions.split(" ").map(val => parseInt(val));
      return [x, y];
    } catch(e) {
      console.log(e.stack);
      return [-1, -1];
    }
  }
  addMove(x: number, y: number) {
    this.moves.push([x, y]);
  }
  getMoves() {
    return this.moves;
  }
}

class Board {
  private board: STATE[][];
  private size: number;
  private recordedMoves: number = 0;

  constructor(size: number) {
    this.size = size;
    this.board = [];
    for (let i = 0; i < size; i++) {
      this.board[i] = [];
      for (let j = 0; j < size; j++) {
        this.board[i][j] = '_';
      }
    }
  }

  addMove(x: number, y: number, sign: STATE): boolean {
    if (x < 0 || x >= this.board.length || y < 0 || y >= this.board[x].length || this.board[x][y] !== '_') {
      return false;
    }
    this.board[x][y] = sign;
    this.recordedMoves++;
    return true;
  }
  print() {
    this.board.forEach(row => console.log(row.join(' ')));
  }
  isWinningMove(x: number, y: number): boolean {
    const target = this.board[x][y];
    if (target === '_') return false;
    let rowWin = 0, colWin = 0, diagonalWin = 0, reverseDiagonalWin = 0;
    for (let i = 0; i < this.size; i++) {
      if (this.board[x][i] === target) rowWin++;
      if (this.board[i][y] === target) colWin++;
      if (this.board[i][i] === target) diagonalWin++;
      if (this.board[i][this.size-i-1] === target) reverseDiagonalWin++
    }

    return rowWin == this.size || colWin == this.size || diagonalWin == this.size || reverseDiagonalWin == this.size;
  }
  isValid() {
    return this.recordedMoves < this.size * this.size;
  }
}

class Game {
	private players: Player[] = [];
  private board: Board;
  private currentIndex: number;

  constructor(size = 3) {
    this.currentIndex = 0;
    this.board = new Board(size);
  }

  async addPlayer() {
    const name: string = await reader.read('Enter name for the player');
    const sign =  states[this.players.length];
    const player = new Player(name, sign);
    this.players.push(player);
    console.log(`Hello ${player.name}. Your sign is ${player.sign}`)
  }

  async start() {
    await this.addPlayer();
    await this.addPlayer();

    let winner = -1;
    while (this.board.isValid()) {
      const currentPlayer = this.players[this.currentIndex];
      const [x, y] = await currentPlayer.makeMove();
      if (!this.board.addMove(x, y, currentPlayer.sign)) {
        console.log('Invalid move, please try again');
      } else {
        currentPlayer.addMove(x, y);
        if (this.board.isWinningMove(x, y)) {
          winner = this.currentIndex;
          break;
        }
        this.board.print();
        this.currentIndex = (this.currentIndex + 1) % this.players.length;
      }
    }
    this.board.print();
    if (winner > 0) {
      console.log(`Congratulations ${this.players[this.currentIndex].name} on your victory.`);
    } else {
      console.log('Match was a draw.');
    }
    this.players.forEach(player => {
      console.log(`Moves ${player.name} ${player.getMoves().map(move => `[${move}]`)}`);
    })
    console.log('Game Over.');
    reader.close();
  }
}

async function main() {
  const game = new Game();
  await game.start();
}

main().catch(console.log);
