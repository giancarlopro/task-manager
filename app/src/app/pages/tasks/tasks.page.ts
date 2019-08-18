import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/interfaces/task';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.page.html',
  styleUrls: ['./tasks.page.scss'],
})
export class TasksPage implements OnInit {
  tasks: Task[] = []

  constructor(private alertController: AlertController) { }

  ngOnInit() {
  }

  refreshTasks(event) {
    this.tasks.push({id: 1, descricao: "Fazer ação 1", nome: "Tarefa 1"})

    setTimeout(() => event.target.complete(), 2000)
  }

  removeTask(id) {
    this.tasks = this.tasks.filter((task) => task.id != id)
  }

  async addTaskAlert() {
    let alerta = await this.alertController.create({
      header: "New Task",
      buttons: [
        "Cancel",
        {
          text: "OK",
          handler: data => {
            if (!(data.nome == '' || (data.nome == '' && data.descricao == ''))) {
              this.tasks.push({id: null, nome: data.nome, descricao: data.descricao})
            }
          }
        }
      ],
      inputs: [
        {
          type: "text",
          name: "nome",
          placeholder: "Nome:"
        },{
          type: "text",
          name: "descricao",
          label: "Descrição:",
          placeholder: "Descrição"
        }
      ]
    })
    await alerta.present()
  }
}
