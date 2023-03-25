<?php
namespace Mediator\RealWorld;

class EventDispatcher {
    private $observers = [];

    public function __construct() {
        $this->observers["*"] = [];
    }
}

interface Observer {
    public function update(string $event, object $emitter, $data = null);
}

class UserRepository {
    private $users = [];

    public function __construct() {
        events();
    }
}

function events(): EventDispatcher {

}
