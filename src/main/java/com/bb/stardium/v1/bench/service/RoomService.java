package com.bb.stardium.v1.bench.service;

import com.bb.stardium.v1.bench.domain.Room;
import com.bb.stardium.v1.bench.domain.repository.RoomRepository;
import com.bb.stardium.v1.bench.dto.RoomResponseDto;
import com.bb.stardium.v1.bench.service.exception.FixedReadyRoomException;
import com.bb.stardium.v1.bench.service.exception.MasterAndRoomNotMatchedException;
import com.bb.stardium.v1.bench.service.exception.NotFoundRoomException;
import com.bb.stardium.v1.player.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    private void checkRoomMaster(Player player, Room room) {
        if (room.isNotMaster(player)) {
            throw new MasterAndRoomNotMatchedException();
        }
    }

    public boolean delete(long roomId, Player loggedInPlayer) {
        Room room = roomRepository.findById(roomId).orElseThrow(NotFoundRoomException::new);
        if (room.isNotMaster(loggedInPlayer)) {
            throw new MasterAndRoomNotMatchedException();
        }
        if (room.isReady()) {
            throw new FixedReadyRoomException();
        }
        roomRepository.delete(room);
        return true;
    }

}
