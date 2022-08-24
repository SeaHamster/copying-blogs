// 五子棋
var canvas = document.getElementById('five2win_canvas');
var context = canvas.getContext('2d');

var me = true; //true黑子， false， 白子
var over = false;
var box = []; //全部落子点
var wins = []; //全部赢的方法 三维数组

for (var i = 0; i < 15; i++) {
    box[i] = [];
    wins[i] = [];
    for (var j = 0; j < 15; j++) {
        box[i][j] = 0;
        wins[i][j] = [];
    }
}


var count = 0; //总共赢的数量572种
//横向
for (var a = 0; a < 15; a++) {
    for (var b = 0; b < 11; b++) {
        //wins[0][0][0]
        //wins[0][1][0]
        //wins[0][2][0]
        for (var c = 0; c < 5; c++) {
            wins[a][b + c][count] = true;
        }
        count++;
    }
}
//纵向
for (var a = 0; a < 15; a++) {
    for (var b = 0; b < 11; b++) {
        //wins[0][0][0]
        //wins[0][1][0]
        //wins[0][2][0]
        for (var c = 0; c < 5; c++) {
            wins[b + c][a][count] = true;
        }
        count++;
    }
}

//斜 '\'
for (var a = 0; a < 11; a++) {
    for (var b = 0; b < 11; b++) {
        //a = 0, b = 10
        //win[0][10]
        //win[1][11]
        for (var c = 0; c < 5; c++) {
            wins[a + c][b + c][count] = true;
        }
        count++;
    }
}
//反斜 '/'
for (var a = 0; a < 11; a++) {
    for (var b = 4; b < 15; b++) {
        //a = 0, b = 0
        //win[0][5]
        //win[1][4]
        for (var c = 0; c < 5; c++) {
            wins[a + c][b - c][count] = true;
        }
        count++;
    }
}
//我方、计算机方总共可以赢的数量，每在某一方式上可以赢的位置下一颗子，myWin[i]++
//当myWin[i] == 5,说明我方在这个方式上赢的落子已经达到5颗，说明我方已经赢了
var myWin = []; //
var computerWin = [];
for (var i = 0; i < count; i++) {
    myWin[i] = 0;
    computerWin[i] = 0;
}

//画纵横线条
function drawLine() {

    context.strokeStyle = '#ccc';
    for (var i = 0; i < 15; i++) {
        context.moveTo(15 + 30 * i, 15);
        context.lineTo(15 + 30 * i, 435);
        context.stroke();

        context.moveTo(15, 15 + 30 * i);
        context.lineTo(435, 15 + 30 * i);
        context.stroke();
    }
}
drawLine()

//走一步，画黑白子，并记录，黑子为1，白子为2
function oneStep(i, j, me) {
    context.beginPath();
    context.arc(15 + i * 30, 15 + j * 30, 13, 0, Math.PI * 2);
    context.closePath();

    var gradient = context.createRadialGradient(15 + i * 30 + 2, 15 + j * 30 - 2, 13, 15 + i * 30 + 2, 15 + j * 30 - 2, 0)
    if (me) { //黑子
        gradient.addColorStop(0, '#0A0A0A');
        gradient.addColorStop(1, '#636766');
        box[i][j] = 1;

    } else { //白子
        gradient.addColorStop(0, '#D1D1D1');
        gradient.addColorStop(1, '#F9F9F9');
        box[i][j] = 2;
    }
    context.fillStyle = gradient;
    context.fill();
}

canvas.onclick = function(e) {
    if (over) return;
    if (!me) return;
    var x = Math.floor(e.offsetX / 30);
    var y = Math.floor(e.offsetY / 30);
    if (box[x][y] == 0) { //判断没有落子
        oneStep(x, y, me);

        for (var k = 0; k < count; k++) { //第几种赢法
            if (wins[x][y][k]) {
                myWin[k]++;
                computerWin[k] = 6; //因为我方在这个点上已经落子，所以计算机不可能在这个点上赢，
                if (myWin[k] == 5) {
                    console.log('你赢了')
                    over = true;
                }
            }
        }
        if (!over) {
            me = !me;
            computerAI();
        }
    }
}

//计算机
function computerAI() {
    var myScore = []; //我方分数
    var computerScore = []; //计算机分数
    var max = 0; //最大分数
    var u = 0,
        v = 0; //最大分数点
    for (var i = 0; i < 15; i++) {
        myScore[i] = [];
        computerScore[i] = [];
        for (var j = 0; j < 15; j++) {
            myScore[i][j] = 0;
            computerScore[i][j] = 0;
        }
    }

    for (var i = 0; i < 15; i++) {
        for (var j = 0; j < 15; j++) {
            if (box[i][j] == 0) { //每个空闲点上进行计算分数
                for (var k = 0; k < count; k++) { //遍历所有可以赢的，数量
                    if (wins[i][j][k]) { //可以赢的点进行算分

                        if (myWin[k] == 1) {
                            myScore[i][j] += 200;
                        } else if (myWin[k] == 2) {
                            myScore[i][j] += 400;
                        } else if (myWin[k] == 3) {
                            myScore[i][j] += 2000;
                        } else if (myWin[k] == 4) {
                            myScore[i][j] += 10000;
                        }

                        if (computerWin[k] == 1) {
                            computerScore[i][j] += 220;
                        } else if (computerWin[k] == 2) {
                            computerScore[i][j] += 420;
                        } else if (computerWin[k] == 3) {
                            computerScore[i][j] += 2100;
                        } else if (computerWin[k] == 4) {
                            computerScore[i][j] += 20000;
                        }
                    }
                }

                //得出最大分数的点，并赋给u,v
                if (myScore[i][j] > max) {
                    max = myScore[i][j];
                    u = i;
                    v = j;
                } else if (myScore[i][j] == max) {
                    if (computerScore[i][j] > computerScore[u][v]) {
                        u = i;
                        v = j;
                    }
                }

                if (computerScore[i][j] > max) {
                    max = computerScore[i][j];
                    u = i;
                    v = j;
                } else if (computerScore[i][j] == max) {
                    if (myScore[i][j] > myScore[u][v]) {
                        u = i;
                        v = j;
                    }
                }

            } //所有空闲点上进行计算分数
        }
    }

    oneStep(u, v, false); //走一步

    for (var k = 0; k < count; k++) { //第几种赢法
        if (wins[u][v][k]) {
            computerWin[k]++;
            myWin[k] = 6;
            if (computerWin[k] == 5) {
                console.log('计算机赢了--')
                over = true;
            }
        }
    }

    if (!over) {
        me = !me;
    }

}