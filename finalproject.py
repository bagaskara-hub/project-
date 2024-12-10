import pygame
import sys

pygame.init()

# Constants (the size and speed of the display from the ball speed and the right/left paddle)
WIDTH, HEIGHT = 700, 500  # display size
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
BALL_SPEED = 5
PADDLE_SPEED = 7
PADDLE_WIDTH, PADDLE_HEIGHT = 15, 60  # size of the paddle "length"

# Initialize screen and font (the title on the display "Pong Game")
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Pong Game")
font = pygame.font.Font(None, 36)  # font size

# Ball position and speed
ball_x, ball_y = WIDTH // 2, HEIGHT // 2
ball_speed_x, ball_speed_y = BALL_SPEED, BALL_SPEED

# Paddle positions (distance of the paddle from the side)
left_paddle_x, right_paddle_x = 10, WIDTH - 25
left_paddle_y, right_paddle_y = HEIGHT // 2 - PADDLE_HEIGHT // 2, HEIGHT // 2 - PADDLE_HEIGHT // 2

# Scores
score_left, score_right = 0, 0

# Game Clock
clock = pygame.time.Clock()

def reset_ball():
    """Resets ball to the center and restarts its speed."""
    return WIDTH // 2, HEIGHT // 2, BALL_SPEED, BALL_SPEED

# Game Loop
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

    # Paddle movement (left paddle controlled by player)
    keys = pygame.key.get_pressed()
    if keys[pygame.K_w] and left_paddle_y > 0:
        left_paddle_y -= PADDLE_SPEED
    if keys[pygame.K_s] and left_paddle_y < HEIGHT - PADDLE_HEIGHT:
        left_paddle_y += PADDLE_SPEED

    # Paddle movement (right paddle controlled by AI)
    if right_paddle_y + PADDLE_HEIGHT // 2 < ball_y:
        right_paddle_y += PADDLE_SPEED
    elif right_paddle_y + PADDLE_HEIGHT // 2 > ball_y:
        right_paddle_y -= PADDLE_SPEED

    # Prevent AI paddle from moving out of bounds
    right_paddle_y = max(0, min(HEIGHT - PADDLE_HEIGHT, right_paddle_y))

    # Ball movement
    ball_x += ball_speed_x
    ball_y += ball_speed_y

    # Ball collision with paddles
    if (
        left_paddle_x < ball_x < left_paddle_x + PADDLE_WIDTH
        and left_paddle_y < ball_y < left_paddle_y + PADDLE_HEIGHT
    ) or (
        right_paddle_x < ball_x < right_paddle_x + PADDLE_WIDTH
        and right_paddle_y < ball_y < right_paddle_y + PADDLE_HEIGHT
    ):
        ball_speed_x = -ball_speed_x

    # Ball collision with top/bottom walls
    if ball_y <= 0 or ball_y >= HEIGHT:
        ball_speed_y = -ball_speed_y

    # Ball out of bounds
    if ball_x <= 0:
        score_right += 1
        ball_x, ball_y, ball_speed_x, ball_speed_y = reset_ball()
    if ball_x >= WIDTH:
        score_left += 1
        ball_x, ball_y, ball_speed_x, ball_speed_y = reset_ball()

    # Drawing/scoring
    screen.fill(BLACK)
    pygame.draw.rect(screen, WHITE, (left_paddle_x, left_paddle_y, PADDLE_WIDTH, PADDLE_HEIGHT))
    pygame.draw.rect(screen, WHITE, (right_paddle_x, right_paddle_y, PADDLE_WIDTH, PADDLE_HEIGHT))
    pygame.draw.ellipse(screen, WHITE, (ball_x - 10, ball_y - 10, 20, 20))
    score_display = font.render(f"{score_left} - {score_right}", True, WHITE)
    screen.blit(score_display, (WIDTH // 2 - 40, 10))

    pygame.display.flip()
    clock.tick(60)
