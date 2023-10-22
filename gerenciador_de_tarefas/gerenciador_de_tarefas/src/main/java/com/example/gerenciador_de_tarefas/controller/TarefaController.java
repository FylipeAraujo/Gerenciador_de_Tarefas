import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        // Certifique-se de que a tarefa exista antes de atualizá-la
        Tarefa tarefaExistente = tarefaService.obterTarefa(id);
        if (tarefaExistente != null) {
            tarefa.setId(id); // Garante que o ID da tarefa seja preservado
            return tarefaService.atualizarTarefa(tarefa);
        } else {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada.");
        }
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
    }
}
