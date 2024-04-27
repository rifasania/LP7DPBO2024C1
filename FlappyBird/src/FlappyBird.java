import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    //player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    ///pipes attribut
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // variabel untuk menyimpan status game
    boolean isGameOver = false;

    // variabel untuk menyimpan skor
    double score = 0;

    // label untuk menampilkan skor
    JLabel scorelabel;

    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    public FlappyBird(){
        setPreferredSize(new Dimension(360, 640));
        setFocusable(true);
        addKeyListener(this);
        // setBackground(Color.blue);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player  = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        scorelabel = new JLabel("Score: 0");
        scorelabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorelabel.setForeground(Color.WHITE);
        add(scorelabel);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

//    public boolean collision(Player player, Pipe pipe){
//
//    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // mendeteksi apakah ada taabrakan dengan pipa
            if(player.getPosX() < pipe.getPosX() + pipe.getWidth() && player.getPosX() + player.getWidth() > pipe.getPosX() &&
                    player.getPosY() < pipe.getPosY() + pipe.getHeight() && player.getPosY() + player.getHeight() > pipe.getPosY()){
                isGameOver = true;
            }

            // jika player berhasil melewati pipa
            if(!pipe.isPassed() && pipe.getPosX() < player.getPosX()) {
                pipe.setPassed(true);
                score += 0.5;
                scorelabel.setText("Score: " + String.valueOf((int) score));
            }
        }

        // mendeteksi player ke bawah
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            isGameOver = true;
        }
    }

    public void placePipes(){
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void restartGame() {
        isGameOver = false;
        score = 0;
        scorelabel.setText("Score: 0");
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(!isGameOver){
            move();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }else if(e.getKeyCode() == KeyEvent.VK_R && isGameOver){
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
