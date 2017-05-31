import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaPessoaComponent } from './tabela-pessoa.component';

describe('TabelaPessoaComponent', () => {
  let component: TabelaPessoaComponent;
  let fixture: ComponentFixture<TabelaPessoaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabelaPessoaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
