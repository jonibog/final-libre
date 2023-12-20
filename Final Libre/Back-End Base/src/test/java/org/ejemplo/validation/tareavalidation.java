public class tareavalidation {
    private void validateTareaParaCrear(String user, Tarea tarea) throws BalanceException {
        if(usersService.findByUser(user).isEmpty()){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea","El usuario no existe");
        }
        if (!usersService.findByUser(user).get().getRole().equalsIgnoreCase("administrador")){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea","Solo un administrador puede crear la tarea");
        }
        if (tarea.getNombre() == null || tarea.getNombre().isBlank() ){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea","Debe contener el nombre de la tarea");
        }
        if (tarea.getEncargado() != null){
            Usuario encargado = tarea.getEncargado();
            if (encargado.getUser() == null){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea", "Debe especificar el nombre de usuario del encargado" );
            }
            Optional<Usuario> encargadoOptional = usersService.findByUser(encargado.getUser());
            if (encargadoOptional.isEmpty()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea", "El encargado no se encuentra registrado en nuestro sistema");
            }
        }
        if (tarea.getFechaRealizado() != null){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede cargar la tarea", "No se puede cargar la tarea con una fecha de realizado");
        }
    }
    public void validateActualizarTarea(String user, Tarea tarea) throws BalanceException {
        if (tarea.getId() == null || tarea.getId().isBlank()){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "Debe especificar el Id de la tarea que desea actualizar");
        }
        if (!tareaRepository.existsById(tarea.getId())){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "El id de la tarea a actualizar no se encuentra registrado en nuestro sistema");
        }
        if(usersService.findByUser(user).isEmpty()){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "El usuario no existe");
        }
        Usuario usuario = usersService.findByUser(user).get();
        if (tarea.getNombre() == null || tarea.getNombre().isBlank() ){
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea","Debe contener el nombre de la tarea");
        }
        if (tarea.getEncargado() == null && !usuario.getRole().equalsIgnoreCase("administrador")) {
            throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "Para modificar la tarea debe ingresar el usuario encargado" );
        }
        if (tarea.getEncargado() != null){
            if (tarea.getEncargado().getUser() == null || tarea.getEncargado().getUser().isBlank()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "Para modificar la tarea debe especificar el nombre de usuario del encargado" );
            }
            Optional<Usuario> encargadoOptional = usersService.findByUser(tarea.getEncargado().getUser());
            if (encargadoOptional.isEmpty()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar la tarea", "El encargado no se encuentra registrado en nuestro sistema");
            }
    }

    public void validateCerrarTarea(String user, String id) throws BalanceException {
            if (id == null || id.isBlank()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "Debe especificar el Id de la tarea que desea finalizar");
            }
            if (!tareaRepository.existsById(id)){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "El id de la tarea a finalizar no se encuentra registrado en nuestro sistema");
            }
            Tarea tareaRegistrada = tareaRepository.findById(id).get();
            if(usersService.findByUser(user).isEmpty()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "El usuario no existe");
            }
            if (tareaRegistrada.getNombre() == null || tareaRegistrada.getNombre().isBlank() ){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea","Debe contener el nombre de la tarea");
            }
            if (tareaRegistrada.getEncargado() == null) {
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "Para finalizar la tarea debe ingresar el usuario encargado" );
            }
            if (tareaRegistrada.getEncargado().getUser() == null ||tareaRegistrada.getEncargado().getUser().isBlank()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "Para finalizar la tarea debe especificar el nombre de usuario del encargado" );
            }
            Optional<Usuario> encargadoOptional = usersService.findByUser(tareaRegistrada.getEncargado().getUser());
            if (encargadoOptional.isEmpty()){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "El encargado no se encuentra registrado en nuestro sistema");
            }
            if (tareaRegistrada.getFechaRealizado() != null){
                throw new BalanceException(HttpStatus.PRECONDITION_FAILED, "No se puede finalizar la tarea", "La tarea ya se encuentra finalizada");
            }
    }
}