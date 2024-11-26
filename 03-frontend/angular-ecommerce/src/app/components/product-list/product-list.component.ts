import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/common/product';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/common/cart-item';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = []
  currentCategoryId: number = 1;
  currentCategoryName: string = "";
  previousCategoryId: number = 1;
  searchMode: boolean = false;

  //nuevas propiedades para la paginacion
  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  previousKeyword: string = "";

  constructor(private productService: ProductService,
              private cartService: CartService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchProducts();
    }
    else {
      this.handleListProducts();
    }

  }

  handleSearchProducts() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;


    //si tenemos una palbra clave diferente a la anterior,
    //entonces establezca pagenumber to 1


    if (this.previousKeyword!= theKeyword) {
      this.thePageNumber = 1;
    }

    this.previousKeyword = theKeyword;

    console.log(`keyword=${theKeyword}, thePageNumber=${this.thePageNumber}`);

    // ahora busque los productos usando palabras clave
    this.productService.searchProductsPaginate(this.thePageNumber - 1,
                                              this.thePageSize,
                                              theKeyword).subscribe(this.processResult());


  }

  handleListProducts() {

    // comprobar si el parámetro "id" está disponible
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // obtenga la cadena de parámetro "id". convertir una cadena a un número usando el símbolo "+"
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
      // obtener la cadena de parámetro "nombre"
      this.currentCategoryName = this.route.snapshot.paramMap.get('name')!;
    }
    else {
      // ID de categoría no disponible... predeterminado en ID de categoría 1
      this.currentCategoryId = 1;
      this.currentCategoryName = 'Productos';
    }

    if (this.previousCategoryId != this.currentCategoryId) {
      this.thePageNumber = 1;
    }

    this.previousCategoryId = this.currentCategoryId;

    console.log(`currentCategoryId=${this.currentCategoryId}, thePageNumber=${this.thePageNumber}`);

    // obtener los productos con la identificación dada 
    this.productService.getProductListPaginate(this.thePageNumber - 1,
                                               this.thePageSize,
                                               this.currentCategoryId)
                                               .subscribe(this.processResult());



    // obtener los productos con la identificación dada 
    this.productService.getProductList(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )    
  }


  updatePageSize(pageSize: string){

    this.thePageSize = +pageSize;
    this.thePageNumber = 1;
    this.listProducts();
  }

  processResult(){
    return (data: any) => {
      this.products = data._embedded.products;
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalElements;
    }
  }


  addToCart(theProduct: Product) {
    
    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.unitPrice}`);

    // TODO ... el trabajo real
    const theCartItem = new CartItem(theProduct);

    this.cartService.addToCart(theCartItem);
  }

}
