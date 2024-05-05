export interface Product {
    itemId: number,
    itemDescription: string,
    totalQuantitySold: number,
    totalSales: number
}
export interface ProductMap {
    [k: string]: Product
}

export interface MonthMap {
    [k: string]: {
        totalQuantitySold: number,
        totalSales: number
    }
}