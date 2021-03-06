const ROOM_APP = (() => {
    'use strict';

    const RoomController = function () {
        const roomService = new RoomService();

        const signUp = () => {
            const signUpButton = document.getElementById('signUp');
            signUpButton ? signUpButton.addEventListener('click', roomService.saveRoom) : undefined;
        };

        const update = () => {
            const updateButton = document.getElementById('update-room-button');
            updateButton ? updateButton.addEventListener('click', roomService.updateRoom) : undefined;
        };

        const join = () => {
            const roomList = document.getElementById('room-list');
            roomList ? roomList.addEventListener('click', roomService.joinRoom) : undefined;
        };

        const quit = () => {
            const quitButton = document.getElementById('quit-button');
            quitButton ? quitButton.addEventListener('click', roomService.quitRoom) : undefined;
        };

        const deleteRoom = () => {
            const deleteButton = document.getElementById('delete-room-button');
            deleteButton ? deleteButton.addEventListener('click', roomService.deleteRoom) : undefined;
        };

        const findRoom = () => {
            const sectionOption = document.getElementById('selectOption');
            sectionOption ? sectionOption.addEventListener('change', roomService.findRoomsBySection) : undefined;
        };

        const searchRoom = () => {
            const searchButton = document.getElementById('search-button');
            searchButton ? searchButton.addEventListener('click', roomService.searchRoomByButton) : undefined;
        };

        const searchInput = () => {
            const searchInput = document.getElementById('search-keyword');
            searchInput ? searchInput.addEventListener('keyup', roomService.searchRoom) : undefined;
        };

        const init = () => {
            signUp();
            update();
            join();
            quit();
            deleteRoom();
            findRoom();
            searchRoom();
            searchInput();
        };

        return {
            init
        }
    };

    const RoomService = function () {
        const connector = FETCH_APP.FetchApi();
        const header = {
            'Content-Type': 'application/json; charset=UTF-8',
            'Accept': 'application/json'
        };

        let roomId = document.getElementById('room-id');
        roomId = roomId ? roomId.value : 0;

        const title = document.getElementById('title');
        const city = document.getElementById('city');
        const section = document.getElementById('section');
        const detail = document.getElementById('detail');
        const startTime = document.getElementById('startTime');
        const endTime = document.getElementById('endTime');
        const playersLimit = document.getElementById('playersLimit');
        const intro = document.getElementById('intro');

        const saveRoom = event => {

            event.preventDefault();

            if (title.value === "" || city.value === "" || section.value === "" || detail.value === "" ||
                startTime.value === "" || endTime.value === "" || playersLimit.value === "" && intro.value === "") {
                alert('모든 항목을 입력해주세요!');
                return;
            }

            const roomBasicInfo = {
                title: title.value,
                intro: intro.value,
                address: {
                    city: city.value,
                    section: section.value,
                    detail: detail.value,
                },
                startTime: startTime.value,
                endTime: endTime.value,
                playersLimit: playersLimit.value,
            };

            const ifSucceed = (response) => {
                response.json().then(data => {
                    window.location.href = `/rooms/${data}`
                })
            };

            connector.fetchTemplate('/rooms',
                connector.POST,
                header,
                JSON.stringify(roomBasicInfo),
                ifSucceed
            );
        };

        const updateRoom = event => {
            event.preventDefault();

            const roomBasicInfo = {
                title: title.value,
                address: {
                    city: city.value,
                    section: section.value,
                    detail: detail.value,
                },
                startTime: startTime.value,
                endTime: endTime.value,
                playersLimit: playersLimit.value,
                intro: intro.value,
            };

            const ifSucceed = (response) => {
                response.json().then(data => {
                    window.location.href = `/rooms/${data}`
                })
            };

            connector.fetchTemplate('/rooms/' + roomId,
                connector.PUT,
                header,
                JSON.stringify(roomBasicInfo),
                ifSucceed
            );
        };

        const joinRoom = (event) => {
            const targetButton = event.target;
            if (targetButton.classList.contains("room-join-button")) {
                let roomId = targetButton.dataset.roomId;

                const ifSucceed = (response) => {
                    alert("방에 입장되었습니다!");
                    response.json().then(data => {
                        window.location.href = `/rooms/${data}`
                    });
                };

                connector.fetchTemplateWithoutBody('/rooms/join/' + roomId,
                    connector.POST,
                    ifSucceed
                );

            }
        };

        const quitRoom = () => {

            const ifSucceed = () => {
                    alert("나가는 데 성공했습니다!");
                    window.location.href = `/rooms`
                };

            connector.fetchTemplateWithoutBody('/rooms/quit/' + roomId,
                connector.POST,
                ifSucceed
            );
        };

        const deleteRoom = () => {
            const ifSucceed = () => {
                alert('방을 삭제하였습니다!');
                window.location.href = '/';
            };

            connector.fetchTemplateWithoutBody('/rooms/' + roomId,
                connector.DELETE,
                ifSucceed
            );
        };

        const findRoomsBySection = () => {
            const selectedOption = document.getElementById('selectOption').selectedOptions[0].value;

            window.location.href = '/' + selectedOption;
        };

        const searchRoom = (event) => {
            if (event.key === "Enter") {
                const searchKeyword = document.getElementById('search-keyword').value;
                window.location.href = `/search/${searchKeyword}`;
            }
        };

        const searchRoomByButton = () => {
            const searchKeyword = document.getElementById('search-keyword').value;
            window.location.href = `/search/${searchKeyword}`;
        };

        return {
            saveRoom,
            updateRoom,
            joinRoom,
            quitRoom,
            deleteRoom,
            findRoomsBySection,
            searchRoom,
            searchRoomByButton
        }
    };

    const init = () => {
        const roomController = new RoomController();
        roomController.init();
    };

    return {
        init
    }
})();

ROOM_APP.init();