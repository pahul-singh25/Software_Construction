from player import *
from abc import ABC, abstractmethod

class game_player(ABC):
    
    @abstractmethod
    def get_stone(self):pass

    def set_name(self,name):pass

    def set_stone(self, stone):pass

    def set_n(self, n):pass

    def get_name(self):pass

    def dummy_strategy(self, Boards):pass

    def capture_strategy(self, Boards):pass

    def capture(self, board, max_liberty):pass

    def find_smallest_coordinates(self, choices):pass

    def make_move(self, Boards):pass

class remote_player(player):
    
    def __init__(self, player, socket):
        self.player = player
        self.socket = socket

    def get_stone(self):
        return self.player.get_stone
    
    def set_name(self,name):
        self.player.set_name(name)

    