package com.example.professor_allocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText cadastroCurso = null;
    private CursoService requestCurso = null;
    private CursoResponse cursoAtualizado = null;
    private EditText etEditarCurso;
    private Button botaoEditar;
    private Button btDelete;
    private Button botaoCadastro;
    private ListView lvListaCurso;

    private List<String> listaNomeCursos;
    private ArrayAdapter<String> adapter;

    private EditText etDepartmentName;
    private EditText etDepartmentId;
    private Button btEnviarDep;
    private Button btEditarDep;
    private ListView lvDepartmentsLists;

    private DepartamentoService requestDepartamento;
    private DepartamentoResponse departamentoResponse;
    private List<String> listaNomeDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaNomeCursos = new ArrayList<>();

        cadastroCurso = findViewById(R.id.etCadastroCurso);
        botaoCadastro = findViewById(R.id.btCadastro);
        etEditarCurso = findViewById(R.id.etEditarCurso);
        btDelete = findViewById(R.id.btDeletar);
        lvListaCurso = findViewById(R.id.lvListarCurso);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaNomeCursos);
        lvListaCurso.setAdapter(adapter);

        requestCurso = new RetrofitConfig()
                .criarService();

        cadastrarCurso();
        editarCurso();
        deletarCurso();





    }

    private void cadastrarCurso() {

        botaoCadastro.setOnClickListener(view -> {
            String conteudo = cadastroCurso.getText().toString();

            CursoPost novoCurso = new CursoPost();
            novoCurso.setName(conteudo);


            requestCurso.createRequest(novoCurso).enqueue(new Callback<CursoResponse>() {
                @Override
                public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                    cursoAtualizado = response.body();

                    String id = Integer.toString(cursoAtualizado.getId());
                    etEditarCurso.setText(id);
                    Toast.makeText(getApplicationContext(), "Curso cadastrado!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CursoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na requisição.", Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    private void editarCurso() {
        botaoEditar = findViewById(R.id.btEditar);

        botaoEditar.setOnClickListener(view -> {
            String editarCurso = cadastroCurso.getText().toString();
            String idDigitado = etEditarCurso.getText().toString();
            int id = Integer.parseInt(idDigitado);

            CursoPost alterarCurso = new CursoPost();
            alterarCurso.setName(editarCurso);

            requestCurso.putRequest(alterarCurso, id).enqueue(new Callback<CursoResponse>() {
                @Override
                public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CursoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na requisição", Toast.LENGTH_LONG).show();

                }
            });
        });


    }

    private void deletarCurso() {

        btDelete.setOnClickListener(view -> {

            String deletarPorid = etEditarCurso.getText().toString();
            int idRecebido = Integer.parseInt(deletarPorid);

            requestCurso.deleteRequest(idRecebido).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Toast.makeText(getApplicationContext(), "Curso deletado!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na request!", Toast.LENGTH_LONG).show();
                }
            });

        });


    }

    public void listarCurso(View view){

        requestCurso.getAllCourses().enqueue(new Callback<List<CursoResponse>>() {
            @Override
            public void onResponse(Call<List<CursoResponse>> call, Response<List<CursoResponse>> response) {
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                List<CursoResponse> cursoLista = response.body();
                for (CursoResponse curso : cursoLista) {
                    Log.i(">>> Resultado", curso.getId() + " " + curso.getName());

                    listaNomeCursos.add(curso.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CursoResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void executarRequestPost(DepartamentoPost departamentoPost) {
        requestDepartamento.createRequest(departamentoPost).enqueue(new Callback<DepartamentoResponse>() {
            @Override
            public void onResponse(Call<DepartamentoResponse> call, Response<DepartamentoResponse> response) {
                departamentoResponse = response.body();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DepartamentoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void putRequest (DepartamentoPost departamentoPut, int id) {
        requestDepartamento.putRequest(departamentoPut, id).enqueue(new Callback<DepartamentoResponse>() {
            @Override
            public void onResponse(Call<DepartamentoResponse> call, Response<DepartamentoResponse> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DepartamentoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestDeleteDep(View view) {
        String idDigitado = etDepartmentId.getText().toString();
        int id = Integer.parseInt(idDigitado);

        requestDepartamento.deleteRequest(id).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void executarRequestGetAll2(View view) {
        requestDepartamento.getAllCourses().enqueue(new Callback<List<DepartamentoResponse>>() {
            @Override
            public void onResponse(Call<List<DepartamentoResponse>> call, Response<List<DepartamentoResponse>> response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                List<DepartamentoResponse> departmentList = response.body();

                for (DepartamentoResponse department : departmentList) {
                    Log.i(">>> Result", department.getId() + " " + department.getName());

                    listaNomeDepartamento.add(department.getName());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DepartamentoResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });

    }
}